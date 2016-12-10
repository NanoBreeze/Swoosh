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
        //escape characters include \", which represents a ".
        //consume the first "
        char currentChar = getNextChar();

        while (currentChar != '"' && currentChar != EOF) { //if a \" appears, in which it's not time to end, then we consume it
            if (Character.isWhitespace(currentChar)) {
                this.text += " ";
                this.value += " ";
                currentChar = getNextChar();
            }
            else if (currentChar == '\\') {
                currentChar = getNextChar();
                if (currentChar == '"') {
                    this.text += "\\\"";
                    this.value += "\\\"";
                    currentChar = getNextChar();
                }
                else {
                    this.text += "\\";
                    this.value += "\\";
                }
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