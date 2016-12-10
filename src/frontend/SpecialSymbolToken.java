package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-09.
 */
public class SpecialSymbolToken extends Token {
    public SpecialSymbolToken (Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception {
        //some special symbols have two characters and some have one
        char currentChar = getCurrentChar();

        switch (currentChar) {
            case '<': //<, <=, <>
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.LESS_EQUALS;
                    currentChar = getNextChar();
                }
                else if (currentChar == '>') {
                    this.type = TokenType.NOT_EQUALS;
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.LESS_THAN; }
                break;
            case '>': //>, >=
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.GREATER_EQUALS;
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.GREATER_THAN; }
                break;
            case '=': //=, ==
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.EQUALS;
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.ASSIGN; }
                break;
            case '.': //., ..
                currentChar = getNextChar();
                if (currentChar == '.') {
                    this.type = TokenType.DOT_DOT;
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.DOT; }
                break;
            default: //the special symbol contains only this one character
                assert(TokenType.getSpecialSymbols().containsKey(Character.toString(currentChar))); //when Scanner creates the object, it should have already checked that the char is/part of a special symbol
                this.type = TokenType.getSpecialSymbols().get(Character.toString(currentChar));
        }
    }
}
