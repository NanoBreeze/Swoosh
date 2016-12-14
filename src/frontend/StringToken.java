package frontend;


import static frontend.Source.EOF;

/**
 * Created by Lenny on 2016-12-09.
 */
public class StringToken extends Token {
    public StringToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception {
        //There are no escape characters
        //consume the first "
        char currentChar = getNextChar();

        while (currentChar != '"' && currentChar != EOF) {
            if (Character.isWhitespace(currentChar)) {
                this.text += " ";
                this.value += " ";
                currentChar = getNextChar();
            }
            else {
                this.text += Character.toString(currentChar);
                this.value += Character.toString(currentChar);
                currentChar = getNextChar();
            }
        }

        //the currentChar is either a " or EOF
        assert(currentChar == '"' || currentChar == EOF);

        if (currentChar == '"') {
            getNextChar(); //consume final quote
            this.type = TokenType.STRING;
        }
        else {
            this.type = TokenType.ERROR;
            this.value = ErrorCode.UNEXPECTED_EOF;
        }
    }
}