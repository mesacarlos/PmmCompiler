package ast;

public abstract class AbstractExpression extends AbstractASTNode implements Expression{
	private boolean lValue;
	public AbstractExpression(int line, int column) {
		super(line, column);
	}


	public void setlValue(boolean lValue) {
		this.lValue = lValue;
	}

	public boolean islValue() {
		return lValue;
	}
}