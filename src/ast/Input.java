package ast;

import ast.visitor.Visitor;

public class Input extends AbstractStatement{
	private Expression expression;

	public Input(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}