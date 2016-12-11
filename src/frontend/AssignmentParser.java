package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.NodeType;
import intermediate.SymTabEntry;

/**
 * Created by Lenny on 2016-12-10.
 */
public class AssignmentParser extends StatementParser{

    public AssignmentParser(Scanner scanner){
        super(scanner);
    }

    public Node parse(Token token) throws Exception{
        Node assignNode = new Node(NodeType.ASSIGN);

        String targetName = token.getText().toLowerCase();
        SymTabEntry targetId = symTab.lookup(targetName);

        if (targetId == null) {
            targetId = symTab.enter(targetName);
        }
        targetId.appendLineNumber(token.getLineNumber());

        token = getNextToken(); //consume

        Node variableNode = new Node(NodeType.VARIABLE);
        variableNode.setAttribute(NodeKey.ID, targetId);

        assignNode.addChild(variableNode);

        //look for the = token
        if (token.getType() == TokenType.EQUALS) {
            token = getNextToken(); //consume the equal sign
        }
        else {
            System.out.println("Something went wrong here!");
        }

        ExpressionParser expressionParser = new ExpressionParser(this.scanner);
        assignNode.addChild(expressionParser.parse(token));

        return assignNode;


    }
}
