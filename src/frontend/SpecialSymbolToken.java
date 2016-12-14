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

        this.text += Character.toString(currentChar);
        switch (currentChar) {
            case '<': //<, <=, <>
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.LESS_EQUALS;
                    this.text += Character.toString(currentChar);

                    currentChar = getNextChar();
                }
                else if (currentChar == '>') {
                    this.type = TokenType.NOT_EQUALS;
                    this.text += Character.toString(currentChar);
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.LESS_THAN; }
                break;
            case '>': //>, >=
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.GREATER_EQUALS;
                    this.text += Character.toString(currentChar);
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.GREATER_THAN; }
                break;
            case '=': //=, ==
                currentChar = getNextChar();
                if (currentChar == '=') {
                    this.type = TokenType.EQUALS;
                    this.text += Character.toString(currentChar);
                    currentChar = getNextChar();
                }
                else { this.type = TokenType.ASSIGN; }
                break;
            default: //the special symbol contains only this one character
                assert(TokenType.getSpecialSymbols().containsKey(Character.toString(currentChar))); //when Scanner creates the object, it should have already checked that the char is/part of a special symbol
                this.type = TokenType.getSpecialSymbols().get(Character.toString(currentChar));
                currentChar = getNextChar();
        }
    }
}
