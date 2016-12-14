package backend;

import intermediate.Node;
import intermediate.NodeType;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-11.
 */
public class LoopExecutor extends StatementExecutor{
    public Object execute(Node node) throws Exception {
        boolean exitLoop = false;
        Node exprNode = null;
        ArrayList<Node> children = node.getChildren();

        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        StatementExecutor statementExecutor = new StatementExecutor();

        while (!exitLoop) {
            for (Node child: children) {
                //check if the test condition is still true
                NodeType childType = child.getType();

                if (childType == NodeType.TEST) {
                    if (exprNode == null) {
                        exprNode = child.getChildren().get(0);
                    }
                    exitLoop = (Boolean) expressionExecutor.execute(exprNode);
                }

                //statement node
                else {
                    statementExecutor.execute(child);
                }

            }
        }
        return null;
    }
}
