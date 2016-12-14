package intermediate;

/**
 * Created by Lenny on 2016-12-10.
 */
public enum NodeType {
    //program structure
    PROGRAM, PROCEDURE, FUNCTION,

    //Statements
    COMPOUND, ASSIGN, LOOP, TEST, CALL, PARAMETERS, IF, SELECT,
    SELECT_BRANCH, SELECT_CONSTANTS, NO_OP,

    //Relational operators
    EQ, NE, LT, LE, GT, GE, NOT,

    //Additive operators
    ADD, SUBTRACT, OR, NEGATE,

    //Multiplicative
    MULTIPLY, DIVIDE, MOD, AND,

    //Operands
    VARIABLE, SUBSCRIPTS, FIELD, NUMBER_CONSTANT,
    STRING_CONSTANT, BOOLEAN_CONSTANT
}
