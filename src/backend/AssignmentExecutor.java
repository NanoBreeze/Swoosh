package backend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.SymTabEntry;
import intermediate.SymTabEntryKey;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-11.
 */
public class AssignmentExecutor extends StatementExecutor{
    public Object execute(Node node) throws Exception {
        ArrayList<Node> children = node.getChildren();
        Node variableNode = children.get(0);
        Node expressionNode = children.get(1);

        //execute the expression and get its value
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        Object value = expressionExecutor.execute(expressionNode);

        //set the value in the symbol table
        SymTabEntry variableId = (SymTabEntry) variableNode.getAttribute(NodeKey.ID);
        variableId.setAttribute(SymTabEntryKey.DATA_VALUE, value);
        return null;
    }
}
