package ast;

import ast.visitor.Visitor;

public class Assignment extends AbstractStatement {
	private Expression left;
	private Expression right;

	public Assignment(int line, int column, Expression assignTo, Expression assignFrom) {
		super(line, column);
		this.left = assignTo;
		this.right = assignFrom;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getLeft() {
		return left;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}