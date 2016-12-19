package backend;

import intermediate.Node;
import intermediate.SymTabStack;

/**
 * Created by Lenny on 2016-12-11.
 */
public class Executor {

    protected static SymTabStack symTabStack;
    protected static Node root;
    protected static RuntimeStack runtimeStack;

    static {
        runtimeStack = new RuntimeStack();
    }
    public void execute(Node node, SymTabStack symTabStack) throws Exception{
        this.root = node;
        this.symTabStack = symTabStack;

        StatementExecutor statementExecutor = new StatementExecutor();
        statementExecutor.execute(root);

    }

    public SymTabStack getSymTabStack() {
        return symTabStack;
    }
}
