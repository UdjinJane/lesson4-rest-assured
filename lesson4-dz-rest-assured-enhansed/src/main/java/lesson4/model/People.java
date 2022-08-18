package lesson4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class People {
    public String name;
    public long age;

    public People(String name, long age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    @Override
    public String toString() {

        return "People{" +
                "name='" + name + '\'' +
                ", age =" + age +
                '}';

    }
}
