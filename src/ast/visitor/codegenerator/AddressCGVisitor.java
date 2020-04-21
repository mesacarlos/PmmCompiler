package ast.visitor.codegenerator;

import ast.*;
import ast.Integer;

//LValues
public class AddressCGVisitor extends AbstractCGVisitor{
	private ValueCGVisitor valueCGVisitor;
	private CodeGenerator cg;

	public AddressCGVisitor(CodeGenerator cg, ValueCGVisitor valueCGVisitor) {
		this.cg = cg;
		this.valueCGVisitor = valueCGVisitor;
	}

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
		obj.getArray().accept(this, params);
		obj.getPosition().accept(valueCGVisitor, params);
		cg.push(Integer.getInstance(), obj.getType().numberOfBytes());
		cg.mul(Integer.getInstance());
		cg.add(Integer.getInstance());
		return null;
	}

	/**
	 *address[[ FieldAccess : expression -> expression field ]]() =
	 * 	address[[expression]]
	 * 	<push>field.offset
	 * 	<add>
	 */
	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.getExpr().accept(this, params);
		Struct struct = (Struct) obj.getExpr().getType();
		for(FieldDefinition fd : struct.getFieldDefinitions())
			if(fd.getName().equals(obj.getField()))
				cg.push(Integer.getInstance(), fd.getOffset());
		cg.add(Integer.getInstance());
		return null;
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
		VarDefinition varDef = (VarDefinition) obj.getDefinition();
		if(obj.getDefinition().getScope() == 0){
			//Global
			cg.pusha(varDef.getOffset());
		}else{
			//Local
			cg.pushBP();
			cg.push(Integer.getInstance(), varDef.getOffset());
			cg.add(Integer.getInstance());
		}
		return null;
	}

}