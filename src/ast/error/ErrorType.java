package ast.error;

import ast.AbstractType;
import ast.Expression;
import ast.Integer;
import ast.Type;
import ast.visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {
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
	public String toString() {
		return "Error " + getLine() + ":" + getColumn() + ": " + message;
	}

	public Type arithmetic(Type type){
		return this;
	}

	public Type arithmetic(){
		return this;
	}

	public Type comparison(Type type){
		return this;
	}

	public Type logic(Type type){
		return this;
	}

	public Type logic(){
		return this;
	}

	public Type promotesTo(Type type){
		return this;
	}

	public boolean isBuiltInType(){
		return true;
	}

	public Type canBeCastTo(Type type){
		return this;
	}

	public Type squareBrackets(Type typeOfBrackets){
		return this;
	}

	public Type dot(String string){
		return this;
	}

	public Type parenthesis(List<Expression> args){
		return this;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}