package ast;

import ast.visitor.Visitor;

import java.util.List;

public class IfElse extends AbstractStatement {
	private Expression condition;
	private List<Statement> sentencesIf;
	private List<Statement> sentencesElse;

	public IfElse(int line, int column, Expression condition, List<Statement> sentencesIf, List<Statement> sentencesElse) {
		super(line, column);
		this.condition = condition;
		this.sentencesIf = sentencesIf;
		this.sentencesElse = sentencesElse;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setSentencesIf(List<Statement> sentencesIf) {
		this.sentencesIf = sentencesIf;
	}

	public List<Statement> getSentencesIf() {
		return sentencesIf;
	}

	public void setSentencesElse(List<Statement> sentencesElse) {
		this.sentencesElse = sentencesElse;
	}

	public List<Statement> getSentencesElse() {
		return sentencesElse;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}