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

        this.text = "";
        this.value = "";

        extract();
    }

    public char getCurrentChar() throws Exception {
        return source.getCurrentChar();
    }

    public char getNextChar() throws Exception {
        return source.getNextChar();
    }

    public int getLineNumber() throws Exception {
        return this.lineNumber;
    }

    public int getStartingPosition() throws Exception {
        return this.startingPosition;
    }

    public String getText() throws Exception {
        return this.text;
    }

    public TokenType getType() throws Exception {
        return this.type;
    }

    //uses the source to deduce the text and valud of the rest of the token
    protected abstract void extract() throws Exception;

}
