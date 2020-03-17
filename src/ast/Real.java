package ast;

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

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}