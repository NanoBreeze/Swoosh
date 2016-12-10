package frontend;

import intermediate.SymTab;
import intermediate.SymTabEntry;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-08.
 * "Parses" the tokens provided by scanner, checks if there are errors and builds stuff from the tokens
 */
public class Parser {
    Scanner scanner;
    SymTab symTab;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
        this.symTab = new SymTab(0);
    }

    public void parse() throws Exception {
        Token token;

        while (!((token = getNextToken()) instanceof EOFToken)) {

            if (token.getType() == TokenType.IDENTIFIER)
            {
                String name = token.getText().toLowerCase();
                //check if the symtab already contains the token

                SymTabEntry entry = symTab.lookup(name);
                if (entry == null) {
                    entry = symTab.enter(name);
                }

                entry.appendLineNumber(token.getLineNumber());
            }
        }

        ArrayList<SymTabEntry> entries = symTab.getAllEntries();

        for (SymTabEntry entry : entries) {
            System.out.println("\n" + entry.getName());
            ArrayList<Integer> lineNumbers = entry.getLineNumbers();
            for (int lineNumber : lineNumbers) {
                System.out.print(Integer.toString(lineNumber) + ", ");
            }
        }
    }

    public Token getNextToken() throws Exception {
        return scanner.getNextToken();
    }


}
