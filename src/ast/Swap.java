package ast;

import ast.visitor.Visitor;

public class Swap extends AbstractStatement {
	private Expression left;
	private Expression right;

	public Swap(int line, int column, Expression left, Expression right){
		super(line, column);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}