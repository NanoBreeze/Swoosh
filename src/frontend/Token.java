package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-08.
 * Very important. Fundamental building blocks. Created from lexemes
 */
public class Token {
    protected int lineNumber;
    protected String text;
    protected Source source;

    public Token(Source source) throws IOException {
        this.source = source;
        this.lineNumber = source.getLineNumber();

        extract();
    }

    //gets the token from the source. For now, each character is a token
    protected void extract() throws IOException {
        text = Character.toString(source.getCurrentChar());

    }

}
