package backend;

import frontend.AssignmentParser;
import intermediate.Node;
import intermediate.NodeType;

/**
 * Created by Lenny on 2016-12-11.
 */
public class StatementExecutor extends Executor{
    public void process() throws Exception {
    }

    public Object execute(Node node) throws Exception {
        NodeType nodeType = (NodeType) node.getType();

        switch (nodeType) {
            case COMPOUND: {
                CompoundExecutor compoundExecutor = new CompoundExecutor();
                return  compoundExecutor.execute(node);
            }

            case ASSIGN: {
                AssignmentExecutor assignmentExecutor = new AssignmentExecutor();
                return assignmentExecutor.execute(node);
            }

            case IF: {
                IfExecutor ifExecutor = new IfExecutor();
                return ifExecutor.execute(node);
            }

            case LOOP: {
                LoopExecutor loopExecutor = new LoopExecutor();
                return loopExecutor.execute(node);
            }

            case CALL: {
                CallExecutor callExecutor = new CallExecutor();
                return callExecutor.execute(node);
            }

            default: {
                System.out.println("StatementExecutor, invalid node type");
                return null;
            }
        }
    }
}
