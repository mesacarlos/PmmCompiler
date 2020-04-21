package ast.visitor.codegenerator;

import ast.*;

//Expressions
public class ValueCGVisitor extends AbstractCGVisitor{
	/**
	 *value [[ Arithmetic : expression -> expression expression ]]() =
	 * 	value[[expression1]]
	 * 	value[[expression2]]
	 * 	if(obj.getOperator.equals("+"))
	 * 		<add>
	 * 	if(obj.getOperator.equals("-"))
	 * 		<sub>
	 * 	if(obj.getOperator.equals("*"))
	 * 		<mul>
	 * 	if(obj.getOperator.equals("/"))
	 * 		<div>
	 *
	 */
	@Override
	public Object visit(Arithmetic obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value [[ ArrayAccess : expression -> position array ]]() =
	 * 	address[[ArrayAccess]]
	 *	<load>arrayAccess.getType().suffix();
	 *
	 */
	@Override
	public Object visit(ArrayAccess obj, Object params) {
		throw new IllegalStateException();
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
		throw new IllegalStateException();
	}

	/**
	 *value [[ CharLiteral : expression -> ]]() =
	 * 	<push>charLiteral.getType().suffix() charLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(CharLiteral obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value [[ Comparison : expression -> expression expression ]]() =
	 * 	value[[expression1]]
	 * 	value[[expression2]]
	 * 	if(comparison.getOperator().equals(">"))
	 * 		<gt>
	 * 	if(comparison.getOperator().equals(">="))
	 * 		<ge>
	 * 	if(comparison.getOperator().equals("<"))
	 * 		<lt>
	 * 	if(comparison.getOperator().equals("<="))
	 * 		<le>
	 * 	if(comparison.getOperator().equals("=="))
	 * 		<eq>
	 * 	if(comparison.getOperator().equals("!="))
	 * 		<ne>
	 *
	 */
	@Override
	public Object visit(Comparison obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value [[ FieldAccess : expression -> expression ]]() =
	 * 	address[[FieldAccess]]
	 * 	<load>fieldAccess.getType().suffix()
	 *
	 *
	 */
	@Override
	public Object visit(FieldAccess obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value [[ IntLiteral : expression -> ]]() =
	 * 	<push>intLiteral.getType().suffix() intLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(IntLiteral obj, Object params) {
		throw new IllegalStateException();
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
		throw new IllegalStateException();
	}

	/**
	 *value [[ RealLiteral : expression -> ]]() =
	 * 	<push>realLiteral.getType().suffix() realLiteral.getValue()
	 *
	 */
	@Override
	public Object visit(RealLiteral obj, Object params) {
		throw new IllegalStateException();
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
		throw new IllegalStateException();
	}

	/**
	 *value [[ UnaryNot : expression -> expression ]]() =
	 * 	value[[expression]]
	 * 	<not>
	 *
	 */
	@Override
	public Object visit(UnaryNot obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *value[[ Variable : expression -> ID ]]() =
	 * 	address[[expression]]()
	 * 	<load>expression.type.suffix()
	 *
	 */
	@Override
	public Object visit(Variable obj, Object params) {
		throw new IllegalStateException();
	}

}