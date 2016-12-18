package frontend;


import intermediate.Node;
import intermediate.NodeType;

/**
 * Created by Lenny on 2016-12-11.
 */
public class WhileParser extends StatementParser{
    public WhileParser(Parser parent) {
        super(parent);
    }

    public Node parse(Token token) throws Exception {
        token = getNextToken(); //consume the WHILE

        //Create LOOP, TEST, and NOT nodes
        Node loopNode = new Node(NodeType.LOOP);
        Node breakNode = new Node(NodeType.TEST);
        Node notNode = new Node(NodeType.NOT);

        loopNode.addChild(breakNode);
        breakNode.addChild(notNode);

        //parse the expression
        ExpressionParser expressionParser = new ExpressionParser(this);
        notNode.addChild(expressionParser.parse(token));

        if (getCurrentToken().getType() != TokenType.DO) {
            System.out.println("Missing DO keyword");
        }
        token = getNextToken();//consume the DO

        //parse the statement
        StatementParser statementParser = new StatementParser(this);
        loopNode.addChild(statementParser.parse(token));

        return loopNode;
    }
}
