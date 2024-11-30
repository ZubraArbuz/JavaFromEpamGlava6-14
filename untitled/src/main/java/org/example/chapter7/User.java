package org.example.chapter7;

import java.util.Comparator;

public class User {
    private int id;
    private String name;
    private int age;
    private String country;

    public User(int id, String name, int age, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', age=" + age + ", country='" + country + "'}";
    }

    public static Comparator<User> byCountryAndAge() {
        return Comparator.comparing(User::getCountry).thenComparing(User::getAge);
    }
}
