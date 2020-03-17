package ast;

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

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}