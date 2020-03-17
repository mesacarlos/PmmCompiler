package ast.error;

import ast.AbstractType;
import ast.Type;
import ast.visitor.Visitor;

public class ErrorType extends AbstractType implements Type {
	private String message;

	public ErrorType(int line, int column, String message) {
		super(line, column);
		this.message = message;
		ErrorHandler.getInstance().addError(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

	@Override
	public String toString() {
		return "Error " + getLine() + ":" + getColumn() + ": " + message;
	}
}