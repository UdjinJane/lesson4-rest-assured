package lesson4.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Root {
    private String name;
    private List<People> people;

    public String getName() {
        return name;
    }


    public List<People> getPeople() {
        return people;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeople(List<People> people) {

        this.people = people;

    }
    @Override
    public String toString() {

        return "Root{" +
                "name='" + name + '\'' +
                ", people =" + people +
                '}';

    }

}
