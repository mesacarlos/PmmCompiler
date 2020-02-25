package ast;

public class UnaryNot extends AbstractExpression{
	private Expression expression;

	public UnaryNot(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

}