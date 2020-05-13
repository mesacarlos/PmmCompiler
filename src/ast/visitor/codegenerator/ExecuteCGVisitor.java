package ast.visitor.codegenerator;

import ast.*;
import ast.Integer;
import ast.Void;

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
		cg.enter(abs(obj.getTotalBytesLocales())); //Allocate space for local variables
		for(VarDefinition varDef : obj.getVariables())
			for(Assignment asgn : varDef.getAssignments())
				asgn.accept(this, params);
		for(Statement stm : obj.getSentences())
			stm.accept(this, obj);

		FuncType funcType = (FuncType)obj.getType();
		if(funcType.getType().equals(ast.Void.getInstance()))
			cg.ret(0, abs(obj.getTotalBytesLocales()), obj.getTotalBytesParams());
		return null;
	}

	/**
	 *execute [[ IfElse : statement -> expression sentencesIf* sentencesElse* ]]() =
	 * int numLabel = cg.getLabel();
	 * 	value[[expression]]
	 * 	<jz> "else"+numLabel
	 * 	for(Statement stm : sentencesIf*)
	 * 		execute[[stm]]
	 * 	<jmp> "end"+numLabel
	 * 	<label>"else"+numLabel
	 * 	for(Statement stm : sentencesElse*)
	 *		execute[[stm]]
	 * 	<label>"end"+numLabel
	 *
	 */
	@Override
	public Object visit(IfElse obj, Object params) {
		int numLabel = cg.getLabel();
		obj.getCondition().accept(valueCGVisitor, params);
		cg.jz("else" + numLabel);
		for(Statement stm : obj.getSentencesIf()){
			stm.accept(this, params);
		}
		cg.jmp("ifEnd" + numLabel);
		cg.label("else" + numLabel);
		for(Statement stm : obj.getSentencesElse()){
			stm.accept(this, params);
		}
		cg.label("ifEnd" + numLabel);
		return null;
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
	 *execute [[Invocation : statement -> expression expression*]]() =
	 * 	value[[(Expression)statement]]()
	 * 	if(((Expression)statement).type instanceof Void == false)
	 * 		<pop> ((Expression)statement).type.suffix
	 *
	 */
	@Override
	public Object visit(Invocation obj, Object params) {
		Expression invocationAsExpr = (Expression)obj;
		invocationAsExpr.accept(valueCGVisitor, params);
		if(invocationAsExpr.getType() instanceof ast.Void == false)
			cg.pop(invocationAsExpr.getType());
		return null;
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
	 *execute[[Return : statement -> expression]](funcDefinition) =
	 * 	value[[expression]]()
	 * 	<ret> expression.type.numberOfBytes() <, >
	 * 		funcDefinition.sumaBytesLocales <, >
	 * 		funcDefinition.sumaBytesParams
	 *
	 */
	@Override
	public Object visit(Return obj, Object params) {
		obj.getExpression().accept(valueCGVisitor, params);
		FuncDefinition fd = (FuncDefinition) params;
		cg.ret(obj.getExpression().getType().numberOfBytes(), abs(fd.getTotalBytesLocales()), fd.getTotalBytesParams());
		return null;
	}

	/**
	 * execute[[Swap : statement -> left right]]() =
	 *  address[[right]]
	 * 	value[[left]]
	 * 	address[[left]]
	 * 	value[[right]]
	 * 	<store>left.getType().suffix()
	 * 	<store>right.getType().suffix()
	 *
	 */
	@Override
	public Object visit(Swap obj, Object params) {
		obj.getRight().accept(addressCGVisitor, params);
		obj.getLeft().accept(valueCGVisitor, params);
		obj.getLeft().accept(addressCGVisitor, params);
		obj.getRight().accept(valueCGVisitor, params);
		cg.store(obj.getLeft().getType());
		cg.store(obj.getRight().getType());
		return null;
	}

	/**
	 *execute [[ VarDefinition : definition -> type ]]() =
	 * 	No hay que hacer nada para las variables globales
	 *
	 */
	@Override
	public Object visit(VarDefinition obj, Object params) {
		//Global variables do not require anything special
		if(obj.getType().equals(Char.getInstance()))
			cg.comment("char " + obj.getName() + "(offset " + obj.getOffset() + ")");
		if(obj.getType().equals(Integer.getInstance()))
			cg.comment("int " + obj.getName() + "(offset " + obj.getOffset() + ")");
		if(obj.getType().equals(Real.getInstance()))
			cg.comment("real " + obj.getName() + "(offset " + obj.getOffset() + ")");

		for(Assignment a : obj.getAssignments())
			a.accept(this, params);
		return null;
	}

	/**
	 *execute [[While : statement -> expression statement*]]() =
	 * 	int labelNumber = cg.getLabel();
	 * 	<label> "while" + labelNumber <:>
	 * 	value[[expression]]()
	 * 	<jz> "endWhile" + labelNumber
	 * 	for(Statement statement : statement*)
	 * 		execute[[statement]]()
	 * 	<jmp> "while" + labelNumber
	 * 	<label> "endWhile" + labelNumber <:>
	 *
	 */
	@Override
	public Object visit(While obj, Object params) {
		int numLabel = cg.getLabel();
		cg.label("while" + numLabel);
		obj.getExpression().accept(valueCGVisitor, params);
		cg.jz("endWhile" + numLabel);
		for(Statement stm : obj.getSentences())
			stm.accept(this, params);
		cg.jmp("while" + numLabel);
		cg.label("endWhile" + numLabel);
		return null;
	}
}