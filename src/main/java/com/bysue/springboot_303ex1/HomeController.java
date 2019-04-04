package com.bysue.springboot_303ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTodos(Model model) {
        model.addAttribute("todo", todoRepository.findAll());
        return "showTodos";
    }

    @GetMapping("/add")
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors())
            return "/add";
        todoRepository.save(todo);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") long id, Model model){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}
