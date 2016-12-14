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
            Source source = new Source("statement.pas");
            Scanner scanner = new Scanner(source);
            Parser parser = new Parser(scanner);
            parser.parse();
        }
        catch(Exception e) {
            System.out.println("Exception caught!");

        }

        A a = new A();
        B b = new B();
        b.c = 4;
        a.b = b;

        Hi(a);


    }

    public static void Hi(A a) {

       B b = new B();
        b.c=6;
        a.b = b;
    }
}

class B {
    public int c;
}
class A {
    public B b;
}