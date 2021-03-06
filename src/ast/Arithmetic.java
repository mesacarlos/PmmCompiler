package ast;

import ast.visitor.Visitor;

public class Arithmetic extends AbstractExpression {
	private String operator;
	private Expression left;
	private Expression right;

	public Arithmetic(int line, int column, String operator, Expression left, Expression right) {
		super(line, column);
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
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