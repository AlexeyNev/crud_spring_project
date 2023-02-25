package org.example.springcourse.controllers;

import org.example.springcourse.dao.PersonDAO;
import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 1) помечаем класс аннотациями
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    /**
     * 8) Внедряем объект PersonDao в контроллер с помощью аннотации @Autowired
     */
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * 2) создаем метод который возвращает список из людей
     * 9) получаем людей из PersonDao и возвращаем шаблон return "people/index";
     * который будет показывать нам весь список людей когда мы перейдем по этому адресу
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    /**
     * 3) создаем метод который возвращает название шаблона и его id
     *
     * @PathVariable("id") данная анотация получит id из url и выведет его на экран,
     * например /people/2 id = 2
     * <p>
     * 10) получаем человека по id и кладем его в модель
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }
}
