package ast.visitor;

import ast.*;
import ast.error.ErrorType;

public class LValueVisitor extends AbstractVisitor{
    @Override
    public Object visit(UnaryMinus obj, Object params) {
        obj.setlValue(false); //Esto está bien, no?
        obj.getExpression().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Variable obj, Object params) {
        obj.setlValue(true);
        return null;
    }

    @Override
    public Object visit(IntLiteral obj, Object params) {
        obj.setlValue(false);
        return null;
    }

    @Override
    public Object visit(RealLiteral obj, Object params) {
        obj.setlValue(false);
        return null;
    }

    @Override
    public Object visit(CharLiteral obj, Object params) {
        obj.setlValue(false);
        return null;
    }

    @Override
    public Object visit(Arithmetic obj, Object params) {
        obj.setlValue(false);
        obj.getLeft().accept(this, params);
        obj.getRight().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Comparison obj, Object params) {
        obj.setlValue(false);
        obj.getLeft().accept(this, params);
        obj.getRight().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Logical obj, Object params) {
        obj.setlValue(false);
        obj.getLeft().accept(this, params);
        obj.getRight().accept(this, params);
        return null;
    }

    @Override
    public Object visit(UnaryNot obj, Object params) {
        obj.setlValue(false); //Esto esta bien, no?
        obj.getExpression().accept(this, params);
        return null;
    }

    @Override
    public Object visit(ArrayAccess obj, Object params) {
        obj.setlValue(true);
        obj.getPosition().accept(this, params);
        obj.getArray().accept(this, params);

        if(!obj.getArray().islValue())
            new ErrorType(obj.getArray().getLine(), obj.getArray().getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");
        return null;
    }

    @Override
    public Object visit(FieldAccess obj, Object params) {
        obj.setlValue(true);
        obj.getExpr().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Cast obj, Object params) {
        //TODO Cast puede ser LValue?
        obj.setlValue(false);
        obj.getCastType().accept(this, params);
        obj.getExpression().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Invocation obj, Object params) {
        obj.setlValue(false);
        //TODO es posible que aquí falte visitar la variable ??
        for(Expression e : obj.getParameters())
            e.accept(this, params);
        return null;
    }

    /**
     * A partir de aquí detectamos errores
     */

    @Override
    public Object visit(Assignment obj, Object params) {
        obj.getLeft().accept(this, params);
        obj.getRight().accept(this, params);

        if(!obj.getLeft().islValue())
            new ErrorType(obj.getLine(), obj.getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");
        return null;
    }

    @Override
    public Object visit(Input obj, Object params) {
        obj.getExpression().accept(this, params);

        if(!obj.getExpression().islValue())
            new ErrorType(obj.getLine(), obj.getColumn(), "[LValue] Error: No se puede asignar un valor a esta expresion.");
        return null;
    }

}