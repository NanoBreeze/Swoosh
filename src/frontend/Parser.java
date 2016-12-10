package frontend;

import sun.reflect.annotation.ExceptionProxy;

/**
 * Created by Lenny on 2016-12-08.
 * "Parses" the tokens provided by scanner, checks if there are errors and builds stuff from the tokens
 */
public class Parser {
    Scanner scanner;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    public void parse() throws Exception {
        Token token;

        while (!((token = getNextToken()) instanceof EOFToken)) {
            System.out.println("Line number: " + token.getLineNumber() + ". Position: " + token.getStartingPosition() + ". Text: " + token.getText() + ". Token type: " + token.getType());
        }
    }

    public Token getNextToken() throws Exception {
        return scanner.getNextToken();
    }


}
