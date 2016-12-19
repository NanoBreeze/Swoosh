package backend;

import intermediate.Node;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-11.
 */
public class IfExecutor extends StatementExecutor{
    public Object execute(Node node) throws Exception {
        ArrayList<Node> children = node.getChildren();
        Node ifNode = children.get(0);
        Node thenNode = children.get(1);
        Node elseNode = children.size() > 2 ? children.get(2) : null ; //else node may or may not exist

        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        StatementExecutor statementExecutor = new StatementExecutor();

        boolean conditionIsTrue = (Boolean) expressionExecutor.execute(ifNode);
        if (conditionIsTrue) {
           statementExecutor.execute(thenNode);
        }
        else if (elseNode != null) {
            statementExecutor.execute(elseNode);
        }

        return null;
    }
}
