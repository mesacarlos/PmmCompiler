package ast;

import java.util.List;

public interface Type extends ASTNode{

    /**
     * Returns true if type is a Logical type (Integer)
     *
     * Implemented in: Integer
     * @return True if integer, false otherwise
     */
    boolean isLogical();

    /**
     * Check if an arithmetic operation can be done between two types
     * An arithmetic operation can only be done between integers or doubles (Ex: 1+3 or 3.14+3.2)
     *
     * Implemented in: Integer, Real
     * @param type Type to check with
     * @return resulting type of the operation, null if operation can't be done
     */
    Type arithmetic(Type type);

    /**
     * Check if type can be negative
     * Used by UnaryMinus only (Ex: -5 or -3.14)
     *
     * Implemented in: Integer, Real
     * @return resulting type of the operation, null if operation can't be done
     */
    Type arithmetic();

    /**
     * Check if two types can be compared
     * Used by Comparison only (Ex: 3>2 or 1.5<4.2)
     *
     * Implemented in: Integer, Real
     * @param type Type to check with
     * @return resulting type of the operation, null if operation can't be done
     */
    Type comparison(Type type);

    /**
     * Check if a logic operation can be done between two types
     * Used by Logical only (Ex: 1&&0)
     *
     * Implemented in: Integer
     * @param type Type to check with
     * @return resulting type of the operation, null if operation can't be done
     */
    Type logic(Type type);

    /**
     * Check if type can be negated
     * Used by UnaryNot only (Ex: !0 or !1)
     *
     * Implemented in: Integer
     * @return resulting type of the operation, null if operation can't be done
     */
    Type logic();

    /**
     * Check if this type can be stored in a variable of the given type
     * A integer can only be stored in a integer, a Real in a Real and a Char in a Char.
     *
     * Implemented in: Integer, Real, Char
     * @param type type to check with
     * @return resulting type of the operation, null if operation can't be done
     */
    Type promotesTo(Type type);

    /**
     * True if built-in type
     *
     * Implemented in: Integer, Real, Char
     * @return true if Integer, Real, Char or ErrorType. False otherwise
     */
    boolean isBuiltInType();

    /**
     * Check if a type can be casted to another type
     * Current allowed casts: b2i, i2b, i2f, f2i
     *
     * Implemented in: Integer, Real, Char
     * @param type type to cast to
     * @return resulting type of the operation, null if operation can't be done
     */
    Type canBeCastTo(Type type);

    /**
     * Gets array type if given type is integer OR null if it isn't an array or given type is not integer.
     * Example: A array of integers will return integer. A integer will return null
     *
     * Implemented in: Array
     * @param type type of element in brackets
     * @return type of this array if given type is integer. Null otherwise
     */
    Type squareBrackets(Type type);

    /**
     * Gets field type or null if undefined
     *
     * Implemented in: Struct
     * @param string field you want to find
     * @return field type or null if undefined
     */
    Type dot(String string);

    /**
     * Check if invocation parameters are correct for that function type
     * Used in Invocation only
     *
     * Implemented in: FuncType
     * @param args list of expressions (parameters of the function invocation)
     * @return Function return type if parameters correct, null otherwise
     */
    Type parenthesis(List<Expression> args);

    /**
     * Get bytes required by this type
     *
     * Implemented in: Integer, Real, Char, Array, Struct
     * @return number of bytes used by this type
     */
    int numberOfBytes();
}