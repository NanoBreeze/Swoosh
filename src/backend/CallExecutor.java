package backend;

import frontend.AssignmentParser;
import intermediate.Node;
import intermediate.NodeKey;
import intermediate.SymTabEntry;
import intermediate.SymTabEntryKey;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-18.
 */
public class CallExecutor extends Executor{

    public Object execute(Node node) throws Exception {
        SymTabEntry routineId = (SymTabEntry) node.getAttribute(NodeKey.ID);
        ActivationRecord activationRecord = new ActivationRecord(routineId);

        //execute the parameters if there are any
        if (node.getChildren().size() > 0) {
            ArrayList<Node> parmNodes = node.getChildren().get(0).getChildren();
            ArrayList<SymTabEntry> parmEntries = (ArrayList<SymTabEntry>) routineId.getAttribute(SymTabEntryKey.ROUTINE_PARMS);
            executeParams(parmNodes , parmEntries);
        }


        runtimeStack.push(activationRecord);

        //Get the root node of the routine's intermediate code
        Node root = (Node) routineId.getAttribute(SymTabEntryKey.ROUTINE_ICODE);

        //Execute the routine
        StatementExecutor statementExecutor = new StatementExecutor();
        Object value = statementExecutor.execute(root);

        runtimeStack.pop();

        return value;

    }

    //All parameters are passed by value
    private void executeParams(ArrayList<Node> parmNodes, ArrayList<SymTabEntry> parmEntries) throws Exception{
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
//        AssignmentExecutor assignmentExecutor = new AssignmentExecutor();

        for (int i = 0; i < parmEntries.size(); i++) {
            SymTabEntry entry = parmEntries.get(i);
            Node node = parmNodes.get(i);

            Object value = expressionExecutor.execute(node);
//            SymTabEntry variableId = (SymTabEntry) variableNode.getAttribute(NodeKey.ID);
            entry.setAttribute(SymTabEntryKey.DATA_VALUE, value);


        }

    }
}
