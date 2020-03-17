package ast.visitor;

import ast.*;
import ast.Integer;
import ast.Void;
import ast.error.ErrorType;

public abstract class AbstractVisitor implements Visitor{

	@Override
	public Object visit(Arithmetic obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Array obj, Object params) {
		obj.getType().accept(this, params);
		return null;
	}

	@Override
	public Object visit(ArrayAccess obj, Object params) {
		obj.getPosition().accept(this, params);
		obj.getArray().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Assignment obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Cast obj, Object params) {
		obj.getCastType().accept(this, params);
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Char obj, Object params) {
		return null;
	}

	@Override
	public Object visit(CharLiteral obj, Object params) {
		return null;
	}

	@Override
	public Object visit(Comparison obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		return null;
	}

	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.getExpr().accept(this, params);
		return null;
	}

	@Override
	public Object visit(FieldDefinition obj, Object params) {
		obj.getType().accept(this, params);
		return null;
	}

	@Override
	public Object visit(FuncDefinition obj, Object params) {
		obj.getType().accept(this, params);
		for(VarDefinition v : obj.getVariables())
			v.accept(this, params);
		for(Statement s : obj.getSentences())
			s.accept(this, params);
		return null;
	}

	@Override
	public Object visit(FuncType obj, Object params) {
		obj.getType().accept(this, params);
		for(VarDefinition v : obj.getVarDefinitions())
			v.accept(this, params);
		return null;
	}

	@Override
	public Object visit(IfElse obj, Object params) {
		obj.getCondition().accept(this, params);
		for(Statement s : obj.getSentencesIf())
			s.accept(this, params);
		for(Statement s : obj.getSentencesElse())
			s.accept(this, params);
		return null;
	}

	@Override
	public Object visit(Input obj, Object params) {
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Integer obj, Object params) {
		return null;
	}

	@Override
	public Object visit(IntLiteral obj, Object params) {
		return null;
	}

	@Override
	public Object visit(Invocation obj, Object params) {
		//TODO es posible que aquí falte visitar la variable ??
		for(Expression e : obj.getParameters())
			e.accept(this, params);
		return null;
	}

	@Override
	public Object visit(Logical obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Print obj, Object params) {
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Program obj, Object params) {
		for(Definition d : obj.getDefinitions())
			d.accept(this, params);
		return null;
	}

	@Override
	public Object visit(Real obj, Object params) {
		return null;
	}

	@Override
	public Object visit(RealLiteral obj, Object params) {
		return null;
	}

	@Override
	public Object visit(Return obj, Object params) {
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Struct obj, Object params) {
		for(FieldDefinition f : obj.getFieldDefinitions())
			f.accept(this, params);
		return null;
	}

	@Override
	public Object visit(UnaryMinus obj, Object params) {
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(UnaryNot obj, Object params) {
		obj.getExpression().accept(this, params);
		return null;
	}

	@Override
	public Object visit(VarDefinition obj, Object params) {
		obj.getType().accept(this, params);
		return null;
	}

	@Override
	public Object visit(Variable obj, Object params) {
		return null;
	}

	@Override
	public Object visit(Void obj, Object params) {
		return null;
	}

	@Override
	public Object visit(While obj, Object params) {
		obj.getExpression().accept(this, params);
		for(Statement s : obj.getSentences())
			s.accept(this, params);
		return null;
	}

	@Override
	public Object visit(ErrorType obj, Object params) {
		return null;
	}
}