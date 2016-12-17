package frontend;

import intermediate.Node;
import intermediate.SymTab;
import intermediate.SymTabEntry;
import intermediate.SymTabEntryKey;

import java.util.ArrayList;

/**
 * Created by Lenny on 2016-12-16.
 */
public class ProgramParser extends Parser{

    public ProgramParser(Parser parent){
        super(parent.scanner);
    }

    public SymTabEntry parse(Token token) throws Exception{
        SymTabEntry routineId = null;
        TokenType routineType = token.getType();

        routineId = parseRoutineName(token);

        routineId.setAttribute(SymTabEntryKey.ROUTINE_SYMTAB, symTabStack.push()); //the routine's identifier (aka, name) is an entry onto the program. The routine itself has its own symbol table,
        //which we push onto the stack so that we can easily add entries to it by calling symTabStack.enterLocal(...)
        token = getCurrentToken();
        assert (token.getType() == TokenType.LEFT_PARENTHESIS); //after function name should be the left parenthesis

        parseParameters(token, routineId);

        token = getCurrentToken();
        if (token.getType() == TokenType.SEMICOLON) {
            token = getNextToken();
        }
        else {
            System.out.println("There should be a colon after routine declaration");
        }

        //parse the definition, which is basically a bunch of statements
        StatementParser statementParser = new StatementParser(this);
        Node root = statementParser.parse(token);

        routineId.setAttribute(SymTabEntryKey.ROUTINE_ICODE, root);
        //we finished parsing the routine
        this.symTabStack.pop();

        return routineId;
    }

    private SymTabEntry parseRoutineName(Token token) throws Exception {
        SymTabEntry routineId = null;

        if (token.getType() == TokenType.IDENTIFIER) {
            String routineName = token.getText().toLowerCase();

            routineId = symTabStack.lookupLocalEntry(routineName);
            if (routineId == null) {
                routineId = symTabStack.enterLocal(routineName);
            }
            else {
                System.out.println("The routine name is already declared as another entry in local symbol table");
            }

            token = getNextToken(); //consume identifier
        }
        else {
            System.out.println("The token isn't an identifier for routine name");
        }

        return routineId;
    }

    private void parseParameters(Token token, SymTabEntry routineId) throws Exception{
        if (token.getType() == TokenType.LEFT_PARENTHESIS) {
            token = getNextToken();
            ArrayList<SymTabEntry> parms = new ArrayList<SymTabEntry>();

            TokenType tokenType = token.getType();

            while (tokenType == TokenType.IDENTIFIER) { //this while loop is wierd.
                String name = token.getText().toLowerCase();
                SymTabEntry symTabEntry = symTabStack.enterLocal(name);
                parms.add(symTabEntry);
                token = getNextToken();

                if (token.getType() == TokenType.COMMA) {
                    token = getNextToken(); //skip
                }
                else {
                    break;
                }
            }

            if (token.getType() == TokenType.RIGHT_PARENTHESIS) {
                token = getNextToken();
            }
            else {
                System.out.println("Missing right parenthesis");
            }

            routineId.setAttribute(SymTabEntryKey.ROUTINE_PARMS, parms);
        }
        else {
            System.out.println("parseParameters(..). The first token isn't (");
        }

    }
}
