package ast;

import ast.visitor.Visitor;

public class Cast extends AbstractExpression {
	private Type castType;
	private Expression expression;

	public Cast(int line, int column, Type type, Expression expression) {
		super(line, column);
		this.castType = type;
		this.expression = expression;
	}

	public void setCastType(Type tipo) {
		this.castType = tipo;
	}

	public Type getCastType() {
		return castType;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}