package lesson4;
import lesson4.dto.AddMealPlan;
import lesson4.dto.Ingredient;
import lesson4.dto.Value;
import lesson4.model.People;
import lesson4.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSimpleParser {
//    public static void main(String[] args) throws IOException {

// Уходим от харкода.

private static final String TAG_NAME_MAIN = "name";
private static final String TAG_PEOPLE = "people";
// private static final String TAG_ELEMENT = "element";
private static final String TAG_NAME = "name";
private static final String TAG_AGE = "age";


   public  Root parse() throws FileNotFoundException {

       Root root = new Root();
       JSONParser  parser = new JSONParser();

       try (FileReader reader = new FileReader("test.json")) {

              JSONObject rootJsonObject = (JSONObject) parser.parse(reader);

              // Парсим рут
              String name = (String) rootJsonObject.get(TAG_NAME_MAIN);

// Парсим масссив  c приведением типа
           List<People> peopleList = new ArrayList<>();

           JSONArray  peopleJsonArray = (JSONArray) rootJsonObject.get(TAG_PEOPLE);
                  for (Object it: peopleJsonArray){
                      JSONObject peopleJsonObject = (JSONObject) it;
                          String namePeople = (String) peopleJsonObject.get(TAG_NAME);
                          Long age = (Long) peopleJsonObject.get(TAG_AGE);
                          // Забрасываем в класс с конструктором
                      People people = new People(namePeople,   age);
                         // и добавляем в лист
                      peopleList.add(people);
                      
                  }
                  root.setName(name);
                  root.setPeople(peopleList);

              return root;

       } catch (Exception e){
           System.out.println("Parsing error" + e.toString());
       }
       


//        ObjectMapper objectMapper = new ObjectMapper();
//        AddMealPlan mealPlanFromFile = objectMapper.readValue(new File("test.json"), AddMealPlan.class);

//converting json to Map
//     byte[] mapData = Files.readAllBytes(Paths.get("test.json"));
//     Map<String,String> myMap = new HashMap<String, String>();
//
//     ObjectMapper objectMapper = new ObjectMapper();
//     myMap = objectMapper.readValue(mapData, HashMap.class);
//     System.out.println("Map is: "+myMap);
//
//
//     // System.out.println(mealPlanFromFile.toString());
        return null;
    }


}
