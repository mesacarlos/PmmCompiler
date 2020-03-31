package ast;

import ast.error.ErrorType;
import ast.visitor.Visitor;

public class Real extends AbstractType{
	private static Real instance;
	
	private Real(int line, int column) {
		super(line, column);
	}
	
	public static Real getInstance() {
		if(instance == null)
			instance = new Real(0, 0);
		return instance;
	}

	public Type arithmetic(Type type){
		if(type.equals(Real.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public Type arithmetic(){
		return this;
	}

	public Type comparison(Type type){
		if(type.equals(Real.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public Type promotesTo(Type type){
		if(type.equals(Real.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public boolean isBuiltInType(){
		return true;
	}

	public Type canBeCastTo(Type type){
		if(type.equals(Integer.getInstance()))
			return type;
		return null;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}