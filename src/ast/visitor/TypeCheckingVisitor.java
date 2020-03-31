package ast.visitor;

import ast.*;
import ast.Integer;
import ast.error.ErrorType;

public class TypeCheckingVisitor extends AbstractVisitor{
	@Override
	public Object visit(Arithmetic obj, Object params) {
		obj.setlValue(false);
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);

		obj.setType(obj.getLeft().getType().arithmetic(obj.getRight().getType()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se puede realizar una operación aritmética entre estos tipos."));
		return null;
	}

	@Override
	public Object visit(ArrayAccess obj, Object params) {
		obj.setlValue(true);
		obj.getPosition().accept(this, params);
		obj.getArray().accept(this, params);

		if(!obj.getArray().islValue())
			new ErrorType(obj.getArray().getLine(), obj.getArray().getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");

		obj.setType(obj.getArray().getType().squareBrackets(obj.getPosition().getType()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] Índice o tipo inválido"));
		return null;
	}

	@Override
	public Object visit(Assignment obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);

		if(!obj.getLeft().islValue())
			new ErrorType(obj.getLine(), obj.getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");

		if(obj.getLeft().getType().promotesTo(obj.getRight().getType()) == null)
			obj.getLeft().setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] Los tipos de esta asignación no coinciden o no son válidos."));
		return null;
	}

	@Override
	public Object visit(Cast obj, Object params) {
		obj.setlValue(false);
		obj.getCastType().accept(this, params);
		obj.getExpression().accept(this, params);

		obj.setType(obj.getExpression().getType().canBeCastTo(obj.getCastType()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] Esta expresión no puede castear al tipo indicado."));
		//TODO En input.txt se hace un cast de double a char.. pero MAPL no lo permite.. deberia dejarlo pasar aquí?
		return null;
	}

	@Override
	public Object visit(Comparison obj, Object params) {
		obj.setlValue(false);
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);

		obj.setType(obj.getLeft().getType().comparison(obj.getRight().getType()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se pueden comparar estos elementos."));
		return null;
	}

	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.setlValue(true);
		obj.getExpr().accept(this, params);

		obj.setType(obj.getExpr().getType().dot(obj.getField()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No existe el campo indicado."));
		return null;
	}

	@Override
	public Object visit(IfElse obj, Object params) {
		obj.getCondition().accept(this, params);

		if(!obj.getCondition().getType().isLogical())
			obj.getCondition().setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] La expresión no es lógica."));

		for(Statement s : obj.getSentencesIf())
			s.accept(this, params);
		for(Statement s : obj.getSentencesElse())
			s.accept(this, params);
		return null;
	}

	@Override
	public Object visit(Input obj, Object params) {
		obj.getExpression().accept(this, params);

		if(!obj.getExpression().islValue())
			new ErrorType(obj.getLine(), obj.getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");

		if(!obj.getExpression().getType().isBuiltInType())
			obj.getExpression().setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se puede leer un tipo no primitivo."));
		return null;
	}

	@Override
	public Object visit(Invocation obj, Object params) {
		obj.setlValue(false);
		obj.getName().accept(this, params);
		for(Expression e : obj.getParameters())
			e.accept(this, params);

		obj.setType(obj.getName().getType().parenthesis(obj.getParameters()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] Los parámetros deben coincidir con la definición de la función."));
		return null;
	}

	@Override
	public Object visit(Logical obj, Object params) {
		obj.setlValue(false);
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);

		obj.setType(obj.getLeft().getType().logic(obj.getRight().getType()));
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] Los tipos no pueden usarse en una operación lógica."));
		return null;
	}

	@Override
	public Object visit(Print obj, Object params) {
		obj.getExpression().accept(this, params);

		if(!obj.getExpression().getType().isBuiltInType())
			obj.getExpression().setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se puede imprimir un tipo no primitivo."));
		return null;
	}

	@Override
	public Object visit(Return obj, Object params) {
		obj.getExpression().accept(this, params);

		//TODO Esto se hace en la próxima clase
		return null;
	}

	@Override
	public Object visit(UnaryMinus obj, Object params) {
		obj.setlValue(false);
		obj.getExpression().accept(this, params);

		obj.setType(obj.getExpression().getType().arithmetic());
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se puede obtener un valor negativo de esta expresión."));
		return null;
	}

	@Override
	public Object visit(UnaryNot obj, Object params) {
		obj.setlValue(false);
		obj.getExpression().accept(this, params);

		obj.setType(obj.getExpression().getType().logic());
		if(obj.getType() == null)
			obj.setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] No se puede negar esta expresión."));
		return null;
	}

	@Override
	public Object visit(Variable obj, Object params) {
		obj.setlValue(true);
		obj.setType(obj.getDefinition().getType());
		return null;
	}

	@Override
	public Object visit(While obj, Object params) { //ok
		obj.getExpression().accept(this, params);

		if(!obj.getExpression().getType().isLogical())
			obj.getExpression().setType(new ErrorType(obj.getLine(), obj.getColumn(), "[TypeChecking] La expresión no es lógica"));

		for(Statement s : obj.getSentences())
			s.accept(this, params);
		return null;
	}

	@Override
	public Object visit(IntLiteral obj, Object params) { //ok
		obj.setlValue(false);
		obj.setType(Integer.getInstance());
		return null;
	}

	@Override
	public Object visit(RealLiteral obj, Object params) { //ok
		obj.setlValue(false);
		obj.setType(Real.getInstance());
		return null;
	}

	@Override
	public Object visit(CharLiteral obj, Object params) { //ok
		obj.setlValue(false);
		obj.setType(Char.getInstance());
		return null;
	}
}