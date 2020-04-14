package ast;

import ast.error.ErrorType;

import java.util.List;

public abstract class AbstractType extends AbstractASTNode implements Type{

	public AbstractType(int line, int column) {
		super(line, column);
	}

	public boolean isLogical(){
		return false;
	}

	public Type arithmetic(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public Type arithmetic(){
		return null;
	}

	public Type comparison(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public Type logic(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public Type logic(){
		return null;
	}

	public Type promotesTo(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public boolean isBuiltInType(){
		return false;
	}

	public Type canBeCastTo(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public Type squareBrackets(Type type){
		if(type instanceof ErrorType)
			return type;
		return null;
	}

	public Type dot(String string){
		return null;
	}

	public Type parenthesis(List<Expression> args){
		return null;
	}

	public int numberOfBytes(){
		new ErrorType(0, 0, "Se ha consultado el tamaño en bytes de un tipo no simple.");
		return 0;
	}

}