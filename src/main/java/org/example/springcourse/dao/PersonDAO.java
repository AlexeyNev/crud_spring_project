package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  5) создаем класс который будет общаться с базой данных (списком)
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 6) создаем метод который будет возвращать список из класса Person (список из людей)
     */
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * From Person", new BeanPropertyRowMapper<>(Person.class));
    }

    /**
     *  7) show(int id). создаем метод который будет возвращать одного человека, он принимает на вход id
     *  и должен найти человека с этим id в базе данных (списке)
     *
     *  проходимся по списку people найти списка с нужным id, если его нет вернем null
     */
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}


