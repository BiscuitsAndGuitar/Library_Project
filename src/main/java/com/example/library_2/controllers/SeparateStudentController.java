package com.example.library_2.controllers;

import com.example.library_2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер для просмотра детальной информации о студенте.
 */
@Controller
public class SeparateStudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Отображает страницу с детальной информацией о студенте
     * @param id ID студента
     * @param model модель для передачи данных в представление
     * @return имя шаблона страницы студента
     */
    @GetMapping("/student/{id}")
    public String studentViewer(@PathVariable long id, Model model) {
        model.addAttribute("student", studentService.findStudentById(id));
        return "student";
    }
}
