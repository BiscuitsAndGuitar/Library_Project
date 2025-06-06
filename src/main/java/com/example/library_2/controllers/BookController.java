package com.example.library_2.controllers;

import com.example.library_2.models.Book;
import com.example.library_2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    //Просмотр списка книг
    @GetMapping("/books")
    public String listBooks(@RequestParam(required = false, name = "keyword") String keyword, Model model) {
        model.addAttribute("books", bookService.findBooksByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "books";
    }

    @PostMapping("/books/search")
    public String searchBooks(@RequestParam(required = false, name = "keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "redirect:/books";
        }
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "redirect:/books?keyword=" + encodedKeyword;
    }

    @PostMapping("/books/reset")
    public String resetBooks() {
        return "redirect:/books";
    }

    //Добавление новой книги
    @GetMapping("/new_book")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "new_book";
    }

    @PostMapping("/new_book/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    //Редактирование книги
    @GetMapping("/edit_book/{id}")
    public String editBook(@PathVariable int id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "edit_book";
    }

    //Удаление книги
    @GetMapping("/delete_book/{id}")
    public String deleteBook(@PathVariable int id, Model model) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
