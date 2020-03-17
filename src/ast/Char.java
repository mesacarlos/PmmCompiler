package ast;

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

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}