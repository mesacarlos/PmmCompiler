package ast;

public interface Expression extends ASTNode{
    boolean islValue();
    Type getType();
    void setType(Type type);
}
