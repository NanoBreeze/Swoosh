package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.NodeType;
import intermediate.SymTabEntry;

/**
 * Created by Lenny on 2016-12-17.
 */
public class CallParser extends StatementParser{
    public CallParser(Parser parent) {
        super(parent);

    }

    public Node parse(Token token) throws Exception {

        Node callNode = new Node(NodeType.CALL);
        SymTabEntry routineEntry = symTabStack.lookup(token.getText().toLowerCase());

        Node parametersNode = new Node(NodeType.PARAMETERS);

        token = getNextToken(); //consume the identifier
        token = getNextToken();  //consume the (

        ExpressionParser expressionParser = new ExpressionParser(this);

        while (token.getType() != TokenType.RIGHT_PARENTHESIS) {
            Node parmNode = expressionParser.parse(token);
            parametersNode.addChild(parmNode);
            token = getCurrentToken();
            if (token.getType() == TokenType.COMMA) {
                token = getNextToken();
            }
        }

        if (token.getType() != TokenType.RIGHT_PARENTHESIS) {
            System.out.println("Missing right parenthesis for calling function");
        }

        token = getNextToken();

        callNode.setAttribute(NodeKey.ID, routineEntry);
        callNode.addChild(parametersNode);

        return callNode;
    }
}
