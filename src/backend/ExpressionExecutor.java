package backend;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DoubleDV;
import com.sun.org.apache.xpath.internal.operations.Bool;
import frontend.Parser;
import intermediate.*;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Created by Lenny on 2016-12-11.
 */
public class ExpressionExecutor extends StatementExecutor{
    public Object execute(Node node) throws Exception {
        NodeType nodeType = (NodeType) node.getType();

        switch (nodeType) {
            case VARIABLE:
                //the variable must already be in the symbol table
                SymTabEntry entry = (SymTabEntry) node.getAttribute(NodeKey.ID);
                return entry.getAttribute(SymTabEntryKey.DATA_VALUE);
            case NUMBER_CONSTANT: return (Double) node.getAttribute(NodeKey.VALUE);
            case STRING_CONSTANT: return (String) node.getAttribute(NodeKey.VALUE);
            case NEGATE: {
                // Get the NEGATE node's expression node child.
                ArrayList<Node> children = node.getChildren();
                Node expressionNode = children.get(0);

                // Execute the expression and return the negative of its value.
                Object value = execute(expressionNode);
                    return -((Double) value);
                }
            default:
                return executeBinaryOperator(node, nodeType);

        }
    }

    private static final EnumSet<NodeType> ARITH_OPS = EnumSet.of(NodeType.ADD, NodeType.SUBTRACT, NodeType.MULTIPLY, NodeType.DIVIDE);

    private Object executeBinaryOperator(Node node, NodeType nodeType) throws Exception{
        //Get the two operand children
        ArrayList<Node> children = node.getChildren();
        Node operandNode1 = children.get(0);
        Node operandNode2 = children.get(1);

        Object operand1 = execute(operandNode1);
        Object operand2 = execute(operandNode2);

        //arithmetic operations
        if (ARITH_OPS.contains(nodeType)) {
                double value1 = (Double) operand1;
                double value2 = (Double) operand2;

                //switch oeprations
                switch (nodeType) {
                    case ADD:
                        return value1 + value2;
                    case SUBTRACT:
                        return value1 - value2;
                    case MULTIPLY:
                        return value1 * value2;
                    case DIVIDE:
                        return (value1 ) / (value2);
                    case MOD:
                        return value1 % value2;
                }
        }
        else if ((nodeType == NodeType.AND) || (nodeType == NodeType.OR)) {
            boolean value1 = (Boolean) operand1;
            boolean value2 = (Boolean) operand2;

            switch (nodeType) {
                case AND: return value1 && value2;
                case OR: return value1 || value2;
            }
        }
        else //relational operators
        {
            double value1 = (Double) operand1;
            double value2 = (Double) operand2;

            switch (nodeType) {
                case EQ: return value1 == value2;
                case NE: return value1 != value2;
                case LT: return value1 < value2;
                case LE: return value1 <= value2;
                case GT: return value1 > value2;
                case GE: return value1 >= value2;
            }
        }

        return 0; //should never reach here
    }
}
