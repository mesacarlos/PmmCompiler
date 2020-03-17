package ast;

import ast.visitor.Visitor;

public class Print extends AbstractStatement {
	private Expression expression;

	public Print(int line, int column, Expression expresion) {
		super(line, column);
		this.expression = expresion;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}