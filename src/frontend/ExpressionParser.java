package frontend;

import intermediate.Node;
import intermediate.NodeKey;
import intermediate.NodeType;
import intermediate.SymTabEntry;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created by Lenny on 2016-12-10.
 */
public class ExpressionParser extends StatementParser{

    public ExpressionParser(Parser parent) {
        super (parent);
    }
    public Node parse(Token token) throws Exception {
        return parseExpression(token);
    }

    private static final EnumSet<TokenType> REL_OPS = EnumSet.of(TokenType.EQUALS, TokenType.NOT_EQUALS,
            TokenType.LESS_THAN, TokenType.LESS_EQUALS, TokenType.GREATER_THAN, TokenType.GREATER_EQUALS);

    private static final HashMap<TokenType, NodeType> REL_OPS_MAP = new HashMap<TokenType, NodeType>();

    static {
        REL_OPS_MAP.put(TokenType.EQUALS, NodeType.EQ);
        REL_OPS_MAP.put(TokenType.NOT_EQUALS, NodeType.NE);
        REL_OPS_MAP.put(TokenType.LESS_THAN, NodeType.LT);
        REL_OPS_MAP.put(TokenType.LESS_EQUALS, NodeType.LE);
        REL_OPS_MAP.put(TokenType.GREATER_THAN, NodeType.GT);
        REL_OPS_MAP.put(TokenType.GREATER_EQUALS, NodeType.GE);

    }

    private Node parseExpression(Token token) throws Exception {
        Node root = parseSimpleExpression(token);
        token = getCurrentToken();

        if (REL_OPS.contains(token.getType())) {
            NodeType nodeType = REL_OPS_MAP.get(token.getType());
            Node newNode = new Node(nodeType);
            newNode.addChild(root);

            token = getNextToken();

            newNode.addChild(parseSimpleExpression(token));

            root = newNode;
        }

        return root;
    }

    private static final EnumSet<TokenType> ADD_OPS = EnumSet.of(TokenType.PLUS, TokenType.MINUS);

    private static final HashMap<TokenType, NodeType> ADD_OPS_OPS_MAP = new HashMap<TokenType, NodeType>();
    static {
        ADD_OPS_OPS_MAP.put(TokenType.PLUS, NodeType.ADD);
        ADD_OPS_OPS_MAP.put(TokenType.MINUS, NodeType.SUBTRACT);
    }

    private Node parseSimpleExpression(Token token) throws Exception {

        TokenType signType = null;
        TokenType tokenType = token.getType();
        //look for a + or -
        if ((tokenType == TokenType.PLUS) || tokenType == TokenType.MINUS) {
            signType = tokenType;
            token = getNextToken();
        }

        Node root = parseTerm(token);
        if (signType == TokenType.MINUS) {
            Node negateNode = new Node(NodeType.NEGATE);
            negateNode.addChild(root);
            root = negateNode;
        }

        token = getCurrentToken();
        tokenType = token.getType();

        while (ADD_OPS.contains(tokenType)) {
            //create an operator node and adopt the current tree as its first child
            NodeType nodeType = ADD_OPS_OPS_MAP.get(tokenType);
            Node opNode = new Node(nodeType);
            opNode.addChild(root);

            token = getNextToken(); //consume the operator
            opNode.addChild(parseTerm(token));

            root = opNode;
            token=getCurrentToken();
            tokenType = token.getType();
        }
        return root;
    }

    private static final EnumSet<TokenType> MULT_OPS = EnumSet.of(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.DIV, TokenType.MOD, TokenType.AND);
    private static final HashMap<TokenType, NodeType> MULT_OPS_OPS_MAP = new HashMap<TokenType, NodeType>();
    static {
        MULT_OPS_OPS_MAP.put(TokenType.MULTIPLY, NodeType.MULTIPLY);
        MULT_OPS_OPS_MAP.put(TokenType.DIVIDE, NodeType.DIVIDE);
        MULT_OPS_OPS_MAP.put(TokenType.MOD, NodeType.MOD);
        MULT_OPS_OPS_MAP.put(TokenType.AND, NodeType.AND);
    }

    private Node parseTerm(Token token) throws Exception {
        Node root = parseFactor(token);

        token = getCurrentToken();
        TokenType tokenType = token.getType();

        //loop over multiplicative factors
        while (MULT_OPS.contains(tokenType)) {
            NodeType nodeType = MULT_OPS_OPS_MAP.get(tokenType);
            Node opNode = new Node(nodeType);
            opNode.addChild(root);

            token = getNextToken();
            tokenType = token.getType();

            opNode.addChild(parseFactor(token));

            token = getNextToken();
            tokenType = token.getType();
        }
        return root;
    }

    private Node parseFactor(Token token) throws Exception {
        TokenType tokenType = token.getType();
        Node root = null;

        switch ((TokenType) tokenType) {
            case IDENTIFIER: {
                //if identifier doesn't exist in stack, it is undefined
                String name = token.getText().toLowerCase();
                SymTabEntry id = symTab.lookup(name);
                if (id == null) {
                    System.out.println("The token:" + name + " is not defined");
                }
                root = new Node(NodeType.VARIABLE);
                root.setAttribute(NodeKey.ID, id);
                id.appendLineNumber(token.getLineNumber());

                token = getNextToken();
                break;
            }

            case NUMBER: {
                root = new Node(NodeType.NUMBER_CONSTANT);
                root.setAttribute(NodeKey.VALUE, token.getValue());

                token = getNextToken();
                break;
            }

            case LEFT_PARENTHESIS: {
                token = getNextToken(); //consume the (
                root = parseExpression(token);

                //look for matching )
                token = getCurrentToken();
                if (token.getType() == TokenType.RIGHT_PARENTHESIS) {
                    token = getNextToken();
                }
                else {
                    System.out.println("MISSING RIGHT PARENTHESIS");
                }
                break;
            }
            default: {
                System.out.println("UNEXPECTED HERE");
                break;
            }
        }

        return root;
    }
}
