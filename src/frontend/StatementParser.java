package frontend;

import intermediate.Node;
import intermediate.NodeKey;

/**
 * Created by Lenny on 2016-12-10.
 */
public class StatementParser extends Parser{

    public StatementParser(Scanner scanner) {
        super(scanner);
    }

    public Node parse(Token token) throws Exception {
        Node statementRoot = null;

        if (token.getType() == TokenType.BEGIN) {
            CompoundParser compoundParser = new CompoundParser(this.scanner);
            statementRoot = compoundParser.parse(token);
        }
        else { //is an identifier, assumes only begin and identifiers in test sample
            AssignmentParser assignmentParser = new AssignmentParser(this.scanner);
            statementRoot = assignmentParser.parse(token);
        }

        setLineNumber(statementRoot, token);
        return statementRoot;

    }

    protected void setLineNumber(Node node, Token token) throws Exception {
        node.setAttribute(NodeKey.LINE, token.getLineNumber());
    }

    protected void parseList(Token token, Node parentNode, TokenType terminator, ErrorCode errorCode) throws Exception {

        while (!(token instanceof EOFToken) && token.getType() != terminator) {
            Node statementNode = parse(token);
            parentNode.addChild(statementNode);

            //check for semicolon
            if (token.getType() == TokenType.SEMICOLON) {
                token = getNextToken();
            }

            else {
                System.out.println("Unexpected end token");
            }
        }

        //loop stopped because of terminator or end of file
        if (token.getType() == terminator) {
            token = getNextToken();
        }
        else {
            System.out.println("Unexpected end of file");
        }
    }
}
