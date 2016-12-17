package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.SymTabEntry;
import intermediate.SymTabEntryType;
import sun.org.mozilla.javascript.internal.ast.IfStatement;

/**
 * Created by Lenny on 2016-12-10.
 */
public class StatementParser extends Parser{

    public StatementParser(Parser parent) {
        super(parent.scanner);
    }

    public Node parse(Token token) throws Exception {
        Node statementRoot = null;

        switch((TokenType) token.getType()) {

            case BEGIN: {
                CompoundParser compoundParser = new CompoundParser(this);
                statementRoot = compoundParser.parse(token);
                break;
            }
            case IDENTIFIER: { //could be a variable or calling a routine

                String name = token.getText().toLowerCase();
                SymTabEntry id = symTabStack.lookup(name);

                Boolean isRoutine;
                if (id != null) {
                    isRoutine = (id.getType() == SymTabEntryType.ASSIGNMENT_VARIABLE) ? false : true;
                }
                else {
                    isRoutine = false;
                }

                if (isRoutine) {
                    CallParser callParser = new CallParser(this);
                    statementRoot = callParser.parse(token);
                }
                else {
                    AssignmentParser assignmentParser = new AssignmentParser(this);
                    statementRoot = assignmentParser.parse(token);
                }

                break;
            }
            case WHILE: {
                WhileParser whileParser = new WhileParser(this);
                statementRoot = whileParser.parse(token);
                break;
            }
            case IF: {
                IfParser ifParser = new IfParser(this);
                statementRoot = ifParser.parse(token);
                break;
            }
            default: {
                System.out.println("Invalid token in StatementParser");
                break;
            }
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

//            printAST(parentNode);

            token = getCurrentToken();

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
            token = getNextToken(); //consume terminator
        }
        else {
            System.out.println("Unexpected end of file");
        }
    }
}
