package ast;

import java.util.List;

public abstract class AbstractType extends AbstractASTNode implements Type{

	public AbstractType(int line, int column) {
		super(line, column);
	}

}