package frontend;

import java.io.IOException;

/**
 * Created by Lenny on 2016-12-09.
 */
public class NumberToken extends Token {
    public NumberToken(Source source) throws Exception {
        super(source);
    }

    //Numbers are either real or integer. Allow positive/negative but no exponents
    public void extract() throws Exception {
        char currentChar = getCurrentChar();
        String integerPart = "";
        String fractionalPart = "";
        String totalNumber = "";

        //if there's a sign in front of the number
        if (currentChar == '-') {
            totalNumber += '-';
            currentChar = getNextChar();
        }
        else if (currentChar == '+') {
            totalNumber += '+';
            currentChar = getNextChar();
        }

        integerPart = getDigits();
        currentChar = getCurrentChar();
        if (currentChar == '.') {
            currentChar = getNextChar();
            fractionalPart = getDigits();
            totalNumber += integerPart + "." + fractionalPart;
        }
        else {
            totalNumber += integerPart;
            this.value = Integer.getInteger(totalNumber);
        }

        if (this.type != TokenType.ERROR) {
            this.value = Double.parseDouble(totalNumber);
            this.text += totalNumber;
            this.type = TokenType.NUMBER;
        }
        //TODO: Check for overflow and underflow numbers
    }

    private String getDigits() throws Exception {
        char currentChar = getCurrentChar();
        String digits = "";

        //if the token was -Letter, which doesn't make sense
        if (!Character.isDigit(currentChar))
        {
            this.type = TokenType.ERROR;
            this.value = ErrorCode.INVALID_NUMBER;
        }

        //extract the digits
        while (Character.isDigit(currentChar)) {
            digits += Character.toString(currentChar);
            currentChar = getNextChar();
        }

        return digits;
    }
}