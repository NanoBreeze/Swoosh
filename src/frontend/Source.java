package frontend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Lenny on 2016-12-08.
 * Reads the program and provides characters to the scanner
 */

public class Source {

    static final char EOF = (char) 0;
    static final char EOL = '\n';

    final int NO_LINES_READ_YET = -1;

    int lineNumber;
    int currentPosition;
    String line;

    BufferedReader bufferedReader;

    public Source(String fileName) throws IOException {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        lineNumber = NO_LINES_READ_YET;
        currentPosition = 0;
    }

    //end of line, end of file, no lines read yet (when we first assign lineNumber in constructor, if we readLine, we can avoid this case), valid character
    public char getCurrentChar() throws IOException{
        if (lineNumber == NO_LINES_READ_YET) {
            readLine(); //lineNumber will become 1
            return getCurrentChar();
        }
        else if (line == null) {
            return EOF;
        }
        else if (currentPosition == line.length()){
            return EOL;
        }
        //suppose the current char is at EOL and the user calls getNextChar()
        else if (currentPosition > line.length()) {
            readLine();
            return getCurrentChar();
        }
        else {
            return line.charAt(currentPosition);
        }
    }

    //consumes the current char by moving the currentPosition forwards by 1. This can also go over the line's length, in which case, getCurrentChar() will automatically read next line
    public char getNextChar() throws Exception{
        currentPosition++;
        return getCurrentChar();
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }


    //a null line will not throw exception. Up to the caller (most likely getCurrentChar()) to decide how to operate on a null line
    private void readLine() throws IOException {
        line = bufferedReader.readLine();

        if (line != null) {
            currentPosition = 0;
            lineNumber++;
        }
    }
}
