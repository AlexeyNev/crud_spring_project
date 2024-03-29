package org.example.springcourse.models;

import javax.validation.constraints.*;

/**
 * 4) создаем модель Person
 */
public class Person {
    private int id;
    @NotEmpty(message = "Name should be between 2 and 20 characters")
    @Size(min = 2, max = 20, message = "Name should not be empty")
    private String name;
    @Min(value = 0, message = "Age < 0 = ERROR")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should not be empty")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address in format: Country, City, Post code (6 digits)")
    private String address;
    //Страна, Город, Почтовый индекс(6 цифр)
    //Russia, Moscow, 123456


    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}