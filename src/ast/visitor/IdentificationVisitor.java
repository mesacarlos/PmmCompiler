package ast.visitor;

import ast.*;
import ast.error.ErrorType;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor{
    SymbolTable st = new SymbolTable();

    @Override
    public Object visit(FuncDefinition obj, Object params) {
        if(!st.insert(obj))
            new ErrorType(obj.getLine(), obj.getColumn(), "[Identification] Ya existe una variable o funcion llamada " + obj.getName());
        st.set();
        obj.getType().accept(this, params);
        for(VarDefinition v : obj.getVariables())
            v.accept(this, params);
        for(Statement s : obj.getSentences())
            s.accept(this, params);
        st.reset();
        return null;
    }

    @Override
    public Object visit(VarDefinition obj, Object params) {
        if(!st.insert(obj))
            new ErrorType(obj.getLine(), obj.getColumn(), "[Identification] Ya existe una variable o funcion llamada " + obj.getName());
        obj.getType().accept(this, params);
        return null;
    }

    @Override
    public Object visit(Variable obj, Object params) {
        Definition def = st.find(obj.getName());
        if(def == null)
            new ErrorType(obj.getLine(), obj.getColumn(), "[Identification] No se ha encontrado la variable o funcion " + obj.getName());
        else
            obj.setDefinition(def);
        return null;
    }

}
