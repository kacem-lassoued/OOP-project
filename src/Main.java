import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStream fin;
        try {
            fin = new FileInputStream("foo.txt");
            int input = fin.read();

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        System.out.print("hello world");
    }
}