package ast;

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

}