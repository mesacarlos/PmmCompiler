package ast.visitor.codegenerator;

import ast.*;
import ast.Integer;

//Expressions
public class ValueCGVisitor extends AbstractCGVisitor{
	private CodeGenerator cg;
	private AddressCGVisitor addressCGVisitor;

	public ValueCGVisitor(CodeGenerator cg){
		this.cg = cg;
		this.addressCGVisitor = new AddressCGVisitor(cg, this);
	}

	/**
	 *value [[ Arithmetic : expression -> expression expression ]]() =
	 * 	value[[expression1]]
	 * 	value[[expression2]]
	 * 	if(obj.getOperator.equals("+"))
	 * 		<add>arithmetic.getType().suffix()
	 * 	if(obj.getOperator.equals("-"))
	 * 		<sub>arithmetic.getType().suffix()
	 * 	if(obj.getOperator.equals("*"))
	 * 		<mul>arithmetic.getType().suffix()
	 * 	if(obj.getOperator.equals("/"))
	 * 		<div>arithmetic.getType().suffix()
	 *
	 */
	@Override
	public Object visit(Arithmetic obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		cg.arithmetic(obj.getOperator(), obj.getType());
		return null;
	}

	/**
	 *value [[ ArrayAccess : expression -> position array ]]() =
	 * 	address[[ArrayAccess]]
	 *	<load>arrayAccess.getType().suffix();
	 *
	 */
	@Override
	public Object visit(ArrayAccess obj, Object params) {
		obj.accept(addressCGVisitor, params);
		cg.load(obj.getType());
		return null;
	}

	/**
	 *value [[ Cast : expression -> castType expression ]]() =
	 * 	value[[expression]]
	 * 	cast.getType().suffix()<2i>
	 * 	if(castType instanceof Integer == false)
	 * 		<i2>castType.suffix()
	 *
	 */
	@Override
	public Object visit(Cast obj, Object params) {
		obj.getExpression().accept(this, params);
		cg.convertTo(obj.getExpression().getType(), obj.getCastType());
		return null;
	}

	/**
	 *value [[ CharLiteral : expression -> ]]() =
	 * 	<push>charLiteral.getType().suffix() charLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(CharLiteral obj, Object params) {
		cg.push(obj.getType(), obj.getValue());
		return null;
	}

	/**
	 *value [[ Comparison : expression -> expression expression ]]() =
	 * 	value[[expression1]]
	 * 	value[[expression2]]
	 * 	if(comparison.getOperator().equals(">"))
	 * 		<gt>arithmetic.getType().suffix()
	 * 	if(comparison.getOperator().equals(">="))
	 * 		<ge>arithmetic.getType().suffix()
	 * 	if(comparison.getOperator().equals("<"))
	 * 		<lt>arithmetic.getType().suffix()
	 * 	if(comparison.getOperator().equals("<="))
	 * 		<le>arithmetic.getType().suffix()
	 * 	if(comparison.getOperator().equals("=="))
	 * 		<eq>arithmetic.getType().suffix()
	 * 	if(comparison.getOperator().equals("!="))
	 * 		<ne>arithmetic.getType().suffix()
	 *
	 */
	@Override
	public Object visit(Comparison obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		cg.comparison(obj.getOperator(), obj.getType());
		return null;
	}

	/**
	 *value [[ FieldAccess : expression -> expression ]]() =
	 * 	address[[FieldAccess]]
	 * 	<load>fieldAccess.getType().suffix()
	 *
	 */
	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.accept(addressCGVisitor, params);
		cg.load(obj.getType());
		return null;
	}

	/**
	 *value [[ IntLiteral : expression -> ]]() =
	 * 	<push>intLiteral.getType().suffix() intLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(IntLiteral obj, Object params) {
		cg.push(obj.getType(), obj.getValue());
		return null;
	}

	/**
	 *
	 *
	 *
	 *
	 */
	@Override
	public Object visit(Invocation obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value [[ Logical : expression -> expression expression ]]() =
	 * 	value[[expression1]]
	 * 	value[[expression2]]
	 * 	if(logical.getOperator().equals("&&"))
	 * 		<and>
	 * 	if(logical.getOperator().equals("||"))
	 * 		<or>
	 *
	 */
	@Override
	public Object visit(Logical obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		cg.logic(obj.getOperator());
		return null;
	}

	/**
	 *value [[ RealLiteral : expression -> ]]() =
	 * 	<push>realLiteral.getType().suffix() realLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(RealLiteral obj, Object params) {
		cg.push(obj.getType(), obj.getValue());
		return null;
	}

	/**
	 *value [[ UnaryMinus : expression -> expression ]]() =
	 * 	if(expression.getType().suffix().equals("i"))
	 * 		<push>Integer.getInstance().suffix() 0
	 * 	else
	 * 		<push>Real.getInstance().suffix() 0
	 *	value[[expression]]
	 * 	<sub>expression.getType().suffix()
	 *
	 */
	@Override
	public Object visit(UnaryMinus obj, Object params) {
		cg.push(obj.getExpression().getType(), 0); //El tipo de la expresion sera Integer o Real ya de por si...
		obj.getExpression().accept(this, params);
		cg.sub(obj.getExpression().getType());
		return null;
	}

	/**
	 *value [[ UnaryNot : expression -> expression ]]() =
	 * 	value[[expression]]
	 * 	<not>
	 *
	 */
	@Override
	public Object visit(UnaryNot obj, Object params) {
		obj.getExpression().accept(this, params);
		cg.not();
		return null;
	}

	/**
	 *value[[ Variable : expression -> ID ]]() =
	 * 	address[[expression]]()
	 * 	<load>expression.type.suffix()
	 *
	 */
	@Override
	public Object visit(Variable obj, Object params) {
		obj.accept(addressCGVisitor, params);
		cg.load(obj.getType());
		return null;
	}

}