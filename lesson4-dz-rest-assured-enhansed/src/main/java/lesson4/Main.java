package lesson4;


import com.fasterxml.jackson.databind.ObjectMapper;
import lesson4.dto.AddMealPlan;
import lesson4.model.Root;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        // парсинг через JSON Simple
        // JsonSimpleParser parser =new JsonSimpleParser();

        // парсинг через GSON
        // GsonParser parser = new GsonParser();
        // Root root = parser.parse();
        // парсинг через Jackson

        // ObjectMapper mapper = new ObjectMapper();
        // Root root = mapper.readValue(new File("test.json"), Root.class);

        // ObjectMapper mapper = new ObjectMapper();
        // AddMealPlan addMealPlan = mapper.readValue("ingredients.json", AddMealPlan.class);
        // AddMealRequest addMealRequest = mapper.readValue("ingredients.json", AddMealRequest.class);

        // GsonParser parser = new GsonParser();
        // Root root = parser.parse();
        // AddMealPlan addMealPlan = parser.parse();

        // System.out.println("Root " + root.toString());




    }


}