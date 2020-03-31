package ast;

import ast.visitor.Visitor;

import java.util.List;

public class Invocation extends AbstractExpression implements Statement {
	private Variable name;
	private List<Expression> parameters;

	public Invocation(int line, int column, Variable name, List<Expression> parameters) {
		super(line, column);
		this.name = name;
		this.parameters = parameters;
	}

	public void setName(Variable name) {
		this.name = name;
	}
	
	public Variable getName() {
		return name;
	}

	public void setParameters(List<Expression> parameters) {
		this.parameters = parameters;
	}

	public List<Expression> getParameters() {
		return parameters;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}