package ast;

import ast.visitor.Visitor;

public class RealLiteral extends AbstractExpression{
	private Double value;

	public RealLiteral(int line, int column, Double value) {
		super(line, column);
		this.value = value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}