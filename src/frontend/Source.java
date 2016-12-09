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
    int position;
    String line;

    BufferedReader bufferedReader;

    public Source(String fileName) throws IOException {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        lineNumber = NO_LINES_READ_YET;
        position = 0;
    }

    //end of line, end of file, no lines read yet (when we first assign lineNumber in constructor, if we readLine, we can avoid this case), valid character
    public char getCurrentChar() throws IOException{
        if (line == null) {
            return EOF;
        }
        else if (position == line.length()){
            return EOL;
        }
        else if (lineNumber == NO_LINES_READ_YET) {
            readLine(); //lineNumber will become 1
            return getCurrentChar();
        }
        else {
            return line.charAt(position);
        }
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition() {
        return position;
    }


    //a null line will not throw exception. Up to the caller (most likely getCurrentChar()) to decide how to operate on a null line
    private void readLine() throws IOException {
        line = bufferedReader.readLine();

        if (line != null) {
            lineNumber++;
        }
    }
}
