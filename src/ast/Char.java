package ast;

import ast.error.ErrorType;
import ast.visitor.Visitor;

public class Char extends AbstractType{
	private static Char instance;

	private Char(int line, int column) {
		super(line, column);
	}
	
	public static Char getInstance() {
		if(instance == null)
			instance = new Char(0, 0);
		return instance;
	}

	public Type promotesTo(Type type){
		if(type.equals(Char.getInstance()) || type instanceof ErrorType)
			return type;
		return null;
	}

	public boolean isBuiltInType(){
		return true;
	}

	public Type canBeCastTo(Type type){
		if(type.equals(Integer.getInstance()) || type.equals(Real.getInstance()) || type.equals(Char.getInstance()))
			return type;
		return null;
	}

	public int numberOfBytes(){
		return 1;
	}

	@Override
	public String suffix() {
		return "b";
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}