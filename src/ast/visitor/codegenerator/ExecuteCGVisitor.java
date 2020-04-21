package ast.visitor.codegenerator;

import ast.*;
import ast.Integer;

import static java.lang.Math.abs;

//Program, Statements, Definitions
public class ExecuteCGVisitor extends AbstractCGVisitor{
	private CodeGenerator cg;
	private AddressCGVisitor addressCGVisitor;
	private ValueCGVisitor valueCGVisitor;

	public ExecuteCGVisitor(CodeGenerator cg){
		this.cg = cg;
		this.valueCGVisitor = new ValueCGVisitor(cg);
		this.addressCGVisitor = new AddressCGVisitor(cg, valueCGVisitor);
	}

	/**
	 *execute [[ Assignment : statement -> expression expression ]]() =
	 * 	address[[expression1]]
	 * 	value[[expression2]]
	 * 	<store>expression1.type.suffix()
	 *
	 */
	@Override
	public Object visit(Assignment obj, Object params) {
		cg.line(obj.getLine());
		obj.getLeft().accept(addressCGVisitor, params);
		obj.getRight().accept(valueCGVisitor, params);
		cg.store(obj.getLeft().getType());
		return null;
	}

	/**
	 *execute [[ FieldDefinition : definition -> type ]]() =
	 * 	<enter>type.numberOfBytes()
	 *
	 */
	@Override
	public Object visit(FieldDefinition obj, Object params) {
		cg.enter(obj.getType().numberOfBytes());
		return null;
	}

	/**
	 *execute [[ FuncDefinition : definition -> statement* varDefinition* ]]() =
	 * 	<label>funcDefinition.getName()
	 * 	for(VarDefinition vd : varDefinition*)
	 * 		<enter>vd.getType().numberOfBytes()
	 * 	execute[[statement*]]
	 * 	if(funcType.getType() instanceof Void)
	 * 		<ret>0, funcDef.getTotalBytesLocales(), funcDef.getTotalBytesParams()
	 *
	 */
	@Override
	public Object visit(FuncDefinition obj, Object params) {
		cg.line(obj.getLine());
		cg.label(obj.getName());
		cg.enter(abs(obj.getTotalBytesLocales()));
		for(Statement stm : obj.getSentences())
			stm.accept(this, params);

		FuncType funcType = (FuncType)obj.getType();
		if(funcType.getType().equals(ast.Void.getInstance()))
			cg.ret(0, abs(obj.getTotalBytesLocales()), obj.getTotalBytesParams());
		return null;
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
		cg.line(obj.getLine());
		obj.getExpression().accept(addressCGVisitor, params);
		cg.in(obj.getExpression().getType());
		cg.store(obj.getExpression().getType());
		return null;
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
		cg.line(obj.getLine());
		obj.getExpression().accept(valueCGVisitor, params);
		cg.out(obj.getExpression().getType());
		return null;
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
		for(Definition def : obj.getDefinitions())
			if(def instanceof VarDefinition)
				def.accept(this, params);
		cg.call("main");
		cg.halt();
		for(Definition def : obj.getDefinitions())
			if(def instanceof FuncDefinition)
				def.accept(this, params);
		return null;
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
	 * 	No hay que hacer nada para las variables globales
	 *
	 */
	@Override
	public Object visit(VarDefinition obj, Object params) {
		//No es necesario realizar nada para las variables globales
		if(obj.getType().equals(Char.getInstance()))
			cg.comment("char " + obj.getName() + "(offset " + obj.getOffset() + ")");
		if(obj.getType().equals(Integer.getInstance()))
			cg.comment("int " + obj.getName() + "(offset " + obj.getOffset() + ")");
		if(obj.getType().equals(Real.getInstance()))
			cg.comment("real " + obj.getName() + "(offset " + obj.getOffset() + ")");
		return null;
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