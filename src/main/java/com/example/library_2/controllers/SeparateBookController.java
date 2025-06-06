package com.example.library_2.controllers;

import com.example.library_2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер для просмотра детальной информации о книге.
 */
@Controller
public class SeparateBookController {
    @Autowired
    private BookService bookService;

    /**
     * Отображает страницу с детальной информацией о книге
     * @param id ID книги
     * @param model модель для передачи данных в представление
     * @return имя шаблона страницы книги
     */
    @GetMapping("/book/{id}")
    public String bookViewer(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "book";
    }
}
