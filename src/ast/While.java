package ast;

import java.util.List;

public class While extends AbstractStatement {
	private Expression expression;
	private List<Statement> sentences;

	public While(int line, int column, Expression expression, List<Statement> sentences) {
		super(line, column);
		this.expression = expression;
		this.sentences = sentences;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public List<Statement> getSentences() {
		return sentences;
	}

	public void setSentences(List<Statement> sentences) {
		this.sentences = sentences;
	}
	

}