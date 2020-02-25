package ast;

public interface Definition extends ASTNode{
	String getName();
	void setScope(int scope);
	int getScope();
}