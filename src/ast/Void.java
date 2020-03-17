package ast;

import ast.visitor.Visitor;

public class Void extends AbstractType{
	private static Void instance;

	private Void(int line, int column) {
		super(line, column);
	}
	
	public static Void getInstance() {
		if(instance == null)
			instance = new Void(0, 0);
		return instance;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}