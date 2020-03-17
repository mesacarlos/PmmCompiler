package ast;

import ast.visitor.Visitor;

import java.util.List;

public class FuncType extends AbstractType {
	private Type type;
	private List<VarDefinition> varDefinitions;

	public FuncType(int line, int column, Type type, List<VarDefinition> varDefinitions) {
		super(line, column);
		this.type = type;
		this.varDefinitions = varDefinitions;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<VarDefinition> getVarDefinitions() {
		return varDefinitions;
	}

	public void setVarDefinitions(List<VarDefinition> varDefinitions) {
		this.varDefinitions = varDefinitions;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}