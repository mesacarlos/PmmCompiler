package ast;

public abstract class AbstractExpression extends AbstractASTNode implements Expression{
	private boolean lValue;
	private Type type;

	public AbstractExpression(int line, int column) {
		super(line, column);
	}

	public void setlValue(boolean lValue) {
		this.lValue = lValue;
	}

	public boolean islValue() {
		return lValue;
	}

	public void setType(Type type){
		this.type = type;
	}

	public Type getType(){
		return type;
	}
}