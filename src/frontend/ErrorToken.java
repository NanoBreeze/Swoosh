package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-09.
 */

public class ErrorToken extends Token {
    public ErrorToken (Source source, ErrorCode errorCode, String tokenText) throws Exception {
        super(source);

        this.text = tokenText;
        this.value = errorCode;
        this.type = TokenType.ERROR;
    }

    public void extract() throws Exception {
        //do nothing
    }
}