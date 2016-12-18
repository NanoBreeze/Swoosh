/**
 * Created by Lenny on 2016-12-09.
 */
import backend.Executor;
import frontend.Parser;
import frontend.Scanner;
import frontend.Source;
import intermediate.Node;
import intermediate.SymTabStack;

class Interpreter
{
    public static void main(String args[]) {
        try {
            Source source = new Source("statement.pas");
            Scanner scanner = new Scanner(source);
            Parser parser = new Parser(scanner);
            parser.parse();

            Node root = parser.getRoot();
            SymTabStack symTabStack = parser.getSymTabStack();

            Executor executor = new Executor();
            executor.execute(root, symTabStack);
        }
        catch(Exception e) {
            System.out.println("Exception caught!");

        }


    }
}
