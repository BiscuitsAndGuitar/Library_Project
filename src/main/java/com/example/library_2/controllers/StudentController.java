package com.example.library_2.controllers;

import com.example.library_2.models.Student;
import com.example.library_2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Контроллер для работы со студентами.
 * Обрабатывает запросы, связанные с управлением студентами.
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Отображает список студентов
     * @param keyword ключевое слово для поиска (опционально)
     * @param model модель для передачи данных в представление
     * @return имя шаблона списка студентов
     */
    @GetMapping("/students")
    public String listStudents(@RequestParam(required = false, name = "keyword") String keyword, Model model) {
        model.addAttribute("students", studentService.findStudentsByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "students";
    }

    /**
     * Обрабатывает поиск студентов
     * @param keyword ключевое слово для поиска
     * @return перенаправление на страницу с результатами поиска
     */
    @PostMapping("/students/search")
    public String searchStudents(@RequestParam(required = false, name = "keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "redirect:/students";
        }
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "redirect:/students?keyword=" + encodedKeyword;
    }

    /**
     * Сбрасывает результаты поиска
     * @return перенаправление на полный список студентов
     */
    @PostMapping("/students/reset")
    public String resetSearch() {
        return "redirect:/students";
    }

    /**
     * Отображает форму добавления нового студента
     * @param model модель для передачи данных в представление
     * @return имя шаблона формы добавления студента
     */
    @GetMapping("/new_student")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }

    /**
     * Обрабатывает сохранение нового студента
     * @param student данные студента из формы
     * @return перенаправление на список студентов
     */
    @PostMapping("/new_student/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    /**
     * Отображает форму редактирования студента
     * @param id ID редактируемого студента
     * @param model модель для передачи данных в представление
     * @return имя шаблона формы редактирования
     */
    @GetMapping("/edit_student/{id}")
    public String editBook(@PathVariable int id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    /**
     * Удаляет студента по ID
     * @param id ID студента для удаления
     * @param model модель для передачи данных в представление
     * @return перенаправление на список студентов
     */
    @GetMapping("/delete_student/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
