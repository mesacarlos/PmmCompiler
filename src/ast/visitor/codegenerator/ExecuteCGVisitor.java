package ast.visitor.codegenerator;

import ast.*;

//Program, Statements, Definitions
public class ExecuteCGVisitor extends AbstractCGVisitor{
	/**
	 *execute [[ Assignment : statement -> expression expression ]]() =
	 * 	address[[expression1]]
	 * 	value[[expression2]]
	 * 	<store>expression.type.suffix()
	 *
	 */
	@Override
	public Object visit(Assignment obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ FieldDefinition : definition -> type ]]() =
	 * 	<enter>type.getSize()
	 *
	 */
	@Override
	public Object visit(FieldDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ FuncDefinition : definition -> statement* varDefinition* ]]() =
	 * 	<label>funcDefinition.getName()
	 * 	for(VarDefinition vd : varDefinition*)
	 * 		<enter>vd.getType().getSize()
	 * 	execute[[statement*]]
	 * 	if(funcDefinition.getType() instanceof Void)
	 * 		<ret>0, funcDef.getTotalBytesLocales(), funcDef.getTotalBytesParams()
	 *
	 */
	@Override
	public Object visit(FuncDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ IfElse : statement -> expression sentencesIf* sentencesElse* ]]() =
	 * 	<label>"if"
	 * 	value[[expression]]
	 * 	<jz> "else"
	 * 	execute[[sentencesIf*]]
	 * 	<label>"else"
	 *	execute[[sentencesElse*]]
	 * 	<label>"end"
	 *
	 */
	@Override
	public Object visit(IfElse obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ Input : statement -> expression ]]() =
	 * 	address[[expression]]()
	 * 	<in>expression.type.suffix()
	 * 	<store>expression.type.suffix()
	 *
	 */
	@Override
	public Object visit(Input obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *
	 *
	 */
	@Override
	public Object visit(Invocation obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ Print : statement -> expression ]]() =
	 * 	value[[expression]]()
	 * 	<out>expression.type.suffix()
	 *
	 */
	@Override
	public Object visit(Print obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ Program : program -> definition* ]]() =
	 * 	for(Definition definition : definition*)
	 * 		if(definition instanceof VarDefinition)
	 * 			execute[[definition]]()
	 *
	 * 	<call main>
	 * 	<halt>
	 *
	 * 	for(Definition definition : definition*)
	 * 		if(definition instanceof FuncDefinition)
	 * 			execute[[ definition ]]()
	 *
	 */
	@Override
	public Object visit(Program obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *
	 *
	 */
	@Override
	public Object visit(Return obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *execute [[ VarDefinition : definition -> type ]]() =
	 * 	<enter>type.getSize()
	 *
	 */
	@Override
	public Object visit(VarDefinition obj, Object params) {
		throw new IllegalStateException();
	}

	/**
	 *
	 *
	 */
	@Override
	public Object visit(While obj, Object params) {
		throw new IllegalStateException();
	}
}