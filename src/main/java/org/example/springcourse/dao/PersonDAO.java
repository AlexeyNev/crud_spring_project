package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  5) создаем класс который будет общаться с базой данных (списком)
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Katy"));
    }

    /**
     * 6) создаем метод который будет возвращать список из класса Person (список из людей)
     */
    public List<Person> index() {
        return people;
    }

    /**     show(int id)
     *  7) создаем метод который будет возвращать одного человека, он принимает на вход id
     *  и должен найти человека с этим id в базе данных (списке)
     *
     *  проходимся по списку people найти списка с нужным id, если его нет вернем null
     */
    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}