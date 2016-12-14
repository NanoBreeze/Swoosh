package frontend;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Lenny on 2016-12-09.
 */
public enum TokenType {

    //Reserved words
    AND, ARRAY, BEGIN, CASE, CONST, DIV, DO, DOWNTO, ELSE, END, FILE, FOR, FUNCTION, GOTO, IF, IN, LABEL, MOD, NIL, NOT,
    OF, OR, PACKED, PROCEDURE, PROGRAM, RECORD, REPEAT, SET, THEN, TO, TYPE, UNTIL, VAR, WHILE, WITH,

    //Special symbols
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), ASSIGN("="), DOT("."), COMMA(","), SEMICOLON(";"), COLON(":"),
    QUOTE("\""), EQUALS("=="), NOT_EQUALS("<>"), LESS_THAN("<"), LESS_EQUALS("<="), GREATER_EQUALS(">="), GREATER_THAN(">"),
    LEFT_PARENTHESIS("("), RIGHT_PARENTHESIS(")"), LEFT_BRACE("{"), RIGHT_BRACE("}"),
    //NOT_EQUALS is <> instead of != for now because it is easier to scan (since there's another symbol with char after <)

    //general token types
    IDENTIFIER, NUMBER, STRING, ERROR;

    private static final int firstReservedIndex = AND.ordinal();
    private static final int lastReservedIndex = WITH.ordinal();
    private static final int firstSpecialSymbolIndex = PLUS.ordinal();
    private static final int lastSpecialSymbolIndex = RIGHT_BRACE.ordinal();

    private static HashMap<String, TokenType> specialSymbols = new HashMap<String, TokenType>();
    private static HashSet<String> reservedwords = new HashSet<String>(); //if reserved words, we get its string value. We use this to match a token's text from the scanner to determine its tokentype


    static {
        TokenType tokentypes[] = TokenType.values();
        for (int i = firstReservedIndex; i < lastReservedIndex; i++) {
            reservedwords.add(tokentypes[i].tokenTypeText);
        }

        for (int i = firstSpecialSymbolIndex; i < lastSpecialSymbolIndex; i++) {
            specialSymbols.put(tokentypes[i].tokenTypeText, tokentypes[i]);
        }
    }



    private String tokenTypeText;

    TokenType() {
        this.tokenTypeText= this.toString().toLowerCase();
    }

    TokenType(String text) {
        this.tokenTypeText = text;
    }

    public static HashMap<String, TokenType> getSpecialSymbols() {
        return specialSymbols;
    }

    public static HashSet<String> getReservedWords() {
        return reservedwords;
    }
}
