package ast.visitor.codegenerator;

import ast.*;

//LValues
public class AddressCGVisitor extends AbstractCGVisitor{

	/**
	 *address[[ ArrayAccess : expression -> position array ]]() =
	 * 	address[[array]]
	 *	value[[position]]
	 * 	<push> obj.getType().numberOfBytes()
	 * 	<mul>
	 * 	<add>
	 */
	@Override
	public Object visit(ArrayAccess obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *address[[ FieldAccess : expression -> expression field ]]() =
	 * 	address[[expression]]
	 * 	<pusha>field.offset
	 * 	<add>
	 */
	@Override
	public Object visit(FieldAccess obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *address[[ Variable : expression -> ID ]]() =
	 * 	if(expression.def.scope == 0)
	 * 		<pusha> expression.def.offset
	 * 	else
	 * 		<push bp>
	 * 		<push> expression.def.offset
	 * 		<add>
	 */
	@Override
	public Object visit(Variable obj, Object params) {
		throw new IllegalStateException();
	}

}