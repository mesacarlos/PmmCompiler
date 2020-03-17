package ast;

import ast.visitor.Visitor;

public class FieldAccess extends AbstractExpression {
	private String field;
	private Expression expr;

	public FieldAccess(int line, int column, Expression expr, String field) {
		super(line, column);
		this.field = field;
		this.expr = expr;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}