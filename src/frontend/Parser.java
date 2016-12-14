package frontend;

import backend.StatementExecutor;
import intermediate.*;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-08.
 * "Parses" the tokens provided by scanner, checks if there are errors and builds stuff from the tokens
 */
public class Parser {
    protected Scanner scanner;
    protected static SymTab symTab;

    static {
        symTab = new SymTab(0);
    }
    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    public void parse() throws Exception {

        Token token = getNextToken();
        Node mostRootNode = null;

        if (token.getType() == TokenType.BEGIN)
        {
            StatementParser statementParser = new StatementParser(this);
            mostRootNode = statementParser.parse(token);
            printAST(mostRootNode);
        }
        else {
            System.out.println("Statement must start with a begin, invalid here!");
        }
    }

    public Token getNextToken() throws Exception {
        return scanner.getNextToken();
    }

    public Token getCurrentToken() throws Exception {
        return scanner.getCurrentToken();
    }

    protected void printAST(Node root) {

        NodeType rootType = root.getType();

        //print the number
        if (rootType == NodeType.NUMBER_CONSTANT){

            String value = root.getAttribute(NodeKey.VALUE).toString();
            System.out.println("<" + rootType.toString() + " value=" + value + " />");
        }
        else if (rootType == NodeType.VARIABLE) {
            String name = ((SymTabEntry) root.getAttribute(NodeKey.ID)).getName();
            System.out.println("<" + rootType.toString() + " id=" + name + " />");
        }
        else {
            System.out.println("<" + rootType.toString() + ">");
        }
        root.getType().toString();

        for (Node node :root.getChildren()) {
            printAST(node);
        }

        //close the type
        if ((rootType != NodeType.VARIABLE) && (rootType != NodeType.NUMBER_CONSTANT)) {
            System.out.println("</" + rootType.toString() + ">");
        }
    }

}
