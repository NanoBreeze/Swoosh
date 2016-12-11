package frontend;

import intermediate.Node;
import intermediate.NodeType;

/**
 * Created by Lenny on 2016-12-11.
 */
public class IfParser extends StatementParser{
    public IfParser(Scanner scanner) {
        super(scanner);
    }

    public Node parse(Token token) throws Exception {
        Node ifNode = new Node(NodeType.IF);

        //Parse the condition
        ExpressionParser expressionParser = new ExpressionParser(this.scanner);
        ifNode.addChild(expressionParser.parse(token));

        token =  getNextToken(); //consume the THEN

        StatementParser statementParser = new StatementParser(this.scanner);
        ifNode.addChild(statementParser.parse(token));

        token = getNextToken(); //consume the else
        ifNode.addChild(statementParser.parse(token));

        return ifNode;
    }
}
