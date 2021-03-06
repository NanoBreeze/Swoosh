package frontend;

import intermediate.Node;
import intermediate.NodeType;

/**
 * Created by Lenny on 2016-12-10.
 */
public class CompoundParser extends StatementParser{

    public CompoundParser(Parser parent) {
        super(parent);

    }

    public Node parse(Token token) throws Exception {
        token = getNextToken();

        //create compound node
        Node compoundNode = new Node(NodeType.COMPOUND);

        StatementParser statementParser = new StatementParser(this);
        statementParser.parseList(token, compoundNode, TokenType.END, ErrorCode.UNEXPECTED_EOF);

        return compoundNode;
    }
}
