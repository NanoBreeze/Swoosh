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
    protected static SymTabStack symTabStack;
    protected static Node root;
    static {
        symTabStack = new SymTabStack();
        root = null;
    }
    public Parser(Scanner scanner){
        this.scanner = scanner;
    }

    public void parse() throws Exception {

        Token token = getNextToken();

        while (token.getType() == TokenType.PROCEDURE) {
            token = getNextToken(); //consume PROCEDURE keyword
            ProgramParser programParser = new ProgramParser(this);
            SymTabEntry programEntry = programParser.parse(token);

            token=getCurrentToken();


            if (token.getType() != TokenType.SEMICOLON) {
                System.out.println("Parser, missing ; after procedure definition");
            }

            token = getNextToken();
//            printRoutineAsSymTabEntry(programEntry);
        }

        if (token.getType() == TokenType.START) {
            token = getNextToken();
            StatementParser statementParser = new StatementParser(this);
            root = statementParser.parse(token);
            printAST(root);
        }
        else {
            System.out.println("Parser. Statement must start with a START token!");
        }
    }

    public Token getNextToken() throws Exception {
        return scanner.getNextToken();
    }

    public Token getCurrentToken() throws Exception {
        return scanner.getCurrentToken();
    }

    public SymTabStack getSymTabStack() {
        return symTabStack;
    }

    public Node getRoot() {
        return root;
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

    protected void printRoutineAsSymTabEntry(SymTabEntry symTabEntry) {
        System.out.println("<SymTabEntry as function. name=" + symTabEntry.getName() + ">");
        System.out.println("<ROUTINE_PARAMS (aka, parameters)>");

        ArrayList<SymTabEntry> routineParams = (ArrayList<SymTabEntry>) symTabEntry.getAttribute(SymTabEntryKey.ROUTINE_PARMS);
        for (SymTabEntry param: routineParams) {
            System.out.println("<SymTabEntry as parameter. name=" + param.getName() + " />");
        }
        System.out.println("</ROUTINE_PARAMS>");

        System.out.println("<ROUTINE_ICODE (aka, definition, statements>");
        Node root = (Node) symTabEntry.getAttribute(SymTabEntryKey.ROUTINE_ICODE);
        printAST(root);
        System.out.println("</ROUTINE_ICODE>");

        System.out.println("</SymTabEntry as function>");
    }

    private void printRoutineAsSymTabEntryInternal(SymTabEntry symTabEntry) {

    }


}
