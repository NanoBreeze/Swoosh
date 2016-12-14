package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.NodeType;
import intermediate.SymTabEntry;

/**
 * Created by Lenny on 2016-12-10.
 */
public class AssignmentParser extends StatementParser{

    public AssignmentParser(Parser parent){
        super(parent);
    }

    public Node parse(Token token) throws Exception{
        Node assignNode = new Node(NodeType.ASSIGN);

        String variableName = token.getText().toLowerCase();
        SymTabEntry variableEntry = symTab.lookup(variableName );

        if (variableEntry == null) {
            variableEntry = symTab.enter(variableName);
        }
        variableEntry.appendLineNumber(token.getLineNumber());

        token = getNextToken(); //consume variable

        Node variableNode = new Node(NodeType.VARIABLE);
        variableNode.setAttribute(NodeKey.ID, variableEntry);

        assignNode.addChild(variableNode);

        //look for the = token
        if (token.getType() == TokenType.ASSIGN) {
            token = getNextToken(); //consume the equal sign
        }
        else {
            System.out.println("Something went wrong here!");
        }

        ExpressionParser expressionParser = new ExpressionParser(this);
        assignNode.addChild(expressionParser.parse(token));

        return assignNode;


    }
}
