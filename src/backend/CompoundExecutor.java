package backend;

import frontend.StatementParser;
import intermediate.Node;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-11.
 */
public class CompoundExecutor extends StatementExecutor{
    public Object execute(Node node) throws Exception{
        //loop over children of compound node
        StatementExecutor statementExecutor = new StatementExecutor();
        ArrayList<Node> children = node.getChildren();
        for (Node child: children) {
            statementExecutor.execute(child);
        }
        return null;
    }
}
