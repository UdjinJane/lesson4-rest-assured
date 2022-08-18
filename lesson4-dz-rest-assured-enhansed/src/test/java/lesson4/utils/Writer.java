package lesson4.utils;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void write (String param) throws IOException {

        FileWriter writer = new FileWriter("src/main/resources/id.txt");
        writer.write("idItems = " + param + "\n");
        writer.close();

    }
}
