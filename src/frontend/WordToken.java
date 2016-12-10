package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-09.
 */
public class WordToken extends Token {
    public WordToken(Source source) throws IOException{
        super(source);
    }

    //words start with a letter and can contain either number, letter, or _
    public void extract() throws Exception{
        //because when this is called, Scanner had already determined the first character
        char currentChar = getCurrentChar();

        do {
            this.text = Character.toString(currentChar);
            currentChar = getNextChar();
        } while (Character.isLetterOrDigit(currentChar) || currentChar == '_');

        this.value = this.text;

        //find token type. For words, the type is either IDENTIFIER, or the specific reserved word

        if (TokenType.getReservedWords().contains(this.text.toLowerCase())) {
            this.type = TokenType.valueOf(this.text.toLowerCase());
        }
        else {
            this.type = TokenType.IDENTIFIER;
        }
    }
}
