package frontend;

import intermediate.Node;
import intermediate.NodeType;

/**
 * Created by Lenny on 2016-12-11.
 */
public class IfParser extends StatementParser{
    public IfParser(Parser parent) {
        super(parent);
    }

    public Node parse(Token token) throws Exception {
        token = getNextToken(); //consume the IF
        Node ifNode = new Node(NodeType.IF);

        //Parse the condition
        ExpressionParser expressionParser = new ExpressionParser(this);
        ifNode.addChild(expressionParser.parse(token));

        token =  getCurrentToken(); //consume the THEN
        if (token.getType() != TokenType.THEN) {
            System.out.println("Didn't find a then token!");
        }

        token = getNextToken();

        StatementParser statementParser = new StatementParser(this);
        ifNode.addChild(statementParser.parse(token));

        token = getCurrentToken();
        if (token.getType() == TokenType.SEMICOLON) { //only compound statements would consume the semicolon
            token = getNextToken();
        }
        if (token.getType() == TokenType.ELSE)
        {
            token = getNextToken(); //consume the else
            ifNode.addChild(statementParser.parse(token));
        }

        return ifNode;
    }
}
