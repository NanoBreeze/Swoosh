package frontend;

import java.io.IOException;

import static frontend.ErrorCode.INVALID_CHARACTER;

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

    public char getNextChar() throws Exception {
        return source.getNextChar();
    }

    public Token getCurrentToken() throws Exception {
        return token;
    }

    public Token getNextToken() throws Exception {
        return extractToken();
    }

    //finds the token and returns it
    public Token extractToken() throws Exception{

        skipWhiteSpaceAndComment();
        char currentChar = getCurrentChar();

        if (Character.isLetter(currentChar)) {
            token = new WordToken(source);
        }
        else if (Character.isDigit(currentChar) || currentChar == '-') {
            token = new NumberToken(source);
        }
        else if (currentChar == '\"') {
            token = new StringToken(source);
        }
        else if (TokenType.getSpecialSymbols().containsKey(Character.toString(currentChar))) {
            token = new SpecialSymbolToken(source);
        }
        else if (currentChar == Source.EOF) {
            token = new EOFToken(source);
        }
        else { //invalid character
            token = new ErrorToken(source, INVALID_CHARACTER, Character.toString(currentChar));
        }
        return token;
    }

    private void skipWhiteSpaceAndComment() throws Exception {
        char currentChar = getCurrentChar();
        while (Character.isWhitespace(currentChar) || currentChar == '{') { //isWhitespace includes carriage returns

            if (currentChar == '{') //keep going until we get to a }. Note {} don't have to match, eg, { {{{ } is valid too
            {
                currentChar = getNextChar(); //recall that getNextChar() consumes the current character by moving to the next character
                while (currentChar != '}') {
                    currentChar = getNextChar();
                }
            }
            else { //is a whitespace
                currentChar = getNextChar();
            }
        }
    }
}
