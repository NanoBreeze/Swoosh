package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-08.
 * Reads characters and creates tokens from the characters
 */
public class Scanner {
    Source source;
    Token token;

    public Scanner(Source source) {
        this.source = source;
    }

    public char getCurrentChar() throws IOException{
        return source.getCurrentChar();
    }

    //finds the token and returns it
    public Token extractToken()  {
        return token; //TODO: find the right token
    }
}
