package ast;

import ast.visitor.Visitor;

public class ArrayAccess extends AbstractExpression {
	private Expression position;
	private Expression array;

	public ArrayAccess(int line, int column, Expression array, Expression position) {
		super(line, column);
		this.array = array;
		this.position = position;
	}

	public void setPosition(Expression position) {
		this.position = position;
	}

	public Expression getPosition() {
		return position;
	}

	public void setArray(Expression array) {
		this.array = array;
	}

	public Expression getArray() {
		return array;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}