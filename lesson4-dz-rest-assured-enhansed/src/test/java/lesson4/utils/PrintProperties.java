package lesson4.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PrintProperties {

    public static void main(String[] args) throws IOException {


        Properties prop = new Properties();
    }

    public void printALL() throws IOException {

        InputStream configFile;

        configFile = new FileInputStream("src/main/resources/my.properties");

        int i;

        while((i=configFile.read())!= -1) {

            System.out.print((char) i);
        }

            System.out.println("\n++++++++++++++++++++++++++++");
            configFile = new FileInputStream("src/main/resources/hash.txt");

            int n;

            while((n=configFile.read())!= -1) {

                System.out.print((char) n);
            }

                System.out.println("\n++++++++++++++++++++++++++++");

                configFile = new FileInputStream("src/main/resources/id.txt");

                int m;

                while((m=configFile.read())!= -1) {

                    System.out.print((char) m);

        }
    }
}
