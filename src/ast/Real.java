package ast;

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

}