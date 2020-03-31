package ast;

import ast.error.ErrorType;
import ast.visitor.Visitor;

public class Integer extends AbstractType{
	private static Integer instance;
	
	private Integer(int line, int column) {
		super(line, column);
	}
	
	public static Integer getInstance() {
		if(instance == null)
			instance = new Integer(0, 0);
		return instance;
	}

	public boolean isLogical(){
		return true;
	}

	public Type arithmetic(Type type){
		if(type.equals(Integer.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public Type arithmetic(){
		return this;
	}

	public Type comparison(Type type){
		if(type.equals(Integer.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public Type logic(Type type){
		if(type.equals(Integer.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public Type logic(){
		return this;
	}

	public Type promotesTo(Type type){
		if(type.equals(Integer.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public boolean isBuiltInType(){
		return true;
	}

	public Type canBeCastTo(Type type){
		if(type.equals(Char.getInstance()) || type.equals(Real.getInstance()))
			return type;
		return null;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}