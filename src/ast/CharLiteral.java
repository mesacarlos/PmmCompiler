package ast;

import ast.visitor.Visitor;

public class CharLiteral extends AbstractExpression{
	private char value;

	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}