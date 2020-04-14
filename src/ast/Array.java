package ast;

import ast.error.ErrorType;
import ast.visitor.Visitor;

public class Array extends AbstractType {
	private int size;
	private Type type;
	
	public Array(int line, int column, Type type, int size) {
		super(line, column);
		this.size = size;
		this.type = type;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type squareBrackets(Type typeOfBrackets){
		if(typeOfBrackets.equals(Integer.getInstance()))
			return type; //Returns the array type
		return null;
	}

	public int numberOfBytes(){
		return getType().numberOfBytes() * getSize();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}