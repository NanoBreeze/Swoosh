/**
 * Created by Lenny on 2016-12-09.
 */
import frontend.Parser;
import frontend.Scanner;
import frontend.Source;
class Interpreter
{
    public static void main(String args[]) {
        try {
            Source source = new Source("routine.pas");
            Scanner scanner = new Scanner(source);
            Parser parser = new Parser(scanner);
            parser.parse();
        }
        catch(Exception e) {
            System.out.println("Exception caught!");

        }


    }
}
