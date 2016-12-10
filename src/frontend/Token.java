package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-08.
 * Very important. Fundamental building blocks. Created from lexemes
 */
public abstract class Token {
    protected int lineNumber;
    protected int startingPosition;
    protected Source source;

    protected String text; //the literal text of the token, eg, '''hi'
    protected Object value; //used by error and the actualy value of the token, eg,'hi'

    protected TokenType type; //an enum eg, IDENTIFIER, ERROR, AND, BEGIN (instead of "RESERVED" type, we specify the reserved words)



    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNumber = source.getLineNumber();
        this.startingPosition = source.getCurrentPosition();

        extract();
    }

    public char getCurrentChar() throws Exception {
        return source.getCurrentChar();
    }

    public char getNextChar() throws Exception {
        return source.getNextChar();
    }

    //uses the source to deduce the text and valud of the rest of the token
    protected abstract void extract() throws Exception;

}
