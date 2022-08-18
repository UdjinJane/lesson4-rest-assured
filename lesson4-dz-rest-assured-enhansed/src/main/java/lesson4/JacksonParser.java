package lesson4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lesson4.model.Root;

import java.io.FileReader;

public class JacksonParser {
    public Root parse(){

        ObjectMapper parser = new ObjectMapper();


        try (FileReader reader = new FileReader("test.json")) {


            Root root = parser.readValue(reader, Root.class);

            return root;

        } catch (Exception e){
            System.out.println("Parsing error" + e.toString());
        }


        return null;
    }
}
