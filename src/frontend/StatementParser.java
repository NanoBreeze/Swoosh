package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import sun.org.mozilla.javascript.internal.ast.IfStatement;

/**
 * Created by Lenny on 2016-12-10.
 */
public class StatementParser extends Parser{

    public StatementParser(Scanner scanner) {
        super(scanner);
    }

    public Node parse(Token token) throws Exception {
        Node statementRoot = null;

        switch((TokenType) token.getType()) {

            case BEGIN: {
                CompoundParser compoundParser = new CompoundParser(this.scanner);
                statementRoot = compoundParser.parse(token);
                break;
            }
            case IDENTIFIER: {
            //is an identifier, assumes only begin and identifiers in test sample
                AssignmentParser assignmentParser = new AssignmentParser(this.scanner);
                statementRoot = assignmentParser.parse(token);
                break;
            }
            case WHILE: {
                WhileParser whileParser = new WhileParser(this.scanner);
                statementRoot = whileParser.parse(tokn);
                break;
            }
            case FOR: {
                ForParser forParser = new ForParser(this.scanner);
                statementRoot = forParser.parse(token);
                break;
            }
            case IF: {
                IfStatementParser ifParser = new IfStatementParser(this.scanner);
                statementRoot = ifParser.parse(token);
                break;
            }
            default: {
                System.out.println("Invalid token in StatementParser");
                break;
            }

            setLineNumber(statementRoot, token);
            return statementRoot;
        }

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
