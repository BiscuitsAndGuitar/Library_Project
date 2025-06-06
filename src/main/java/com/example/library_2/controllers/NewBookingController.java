package com.example.library_2.controllers;

import com.example.library_2.services.BookService;
import com.example.library_2.services.CurrentBookingService;
import com.example.library_2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для оформления новых бронирований.
 * Обрабатывает процесс выдачи книг студентам.
 */
@Controller
public class NewBookingController {
    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CurrentBookingService currentBookingService;

    /**
     * Отображает список книг для выдачи
     * @param keyword ключевое слово для поиска (опционально)
     * @param model модель для передачи данных в представление
     * @return имя шаблона выбора книги
     */
    @GetMapping("/new_booking")
    public String listBooks(@RequestParam(required = false, name = "keyword") String keyword, Model model) {
        model.addAttribute("books", bookService.findBooksByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "new_booking";
    }

    /**
     * Отображает форму выбора студента для выдачи книги
     * @param bookId ID выбранной книги
     * @param model модель для передачи данных в представление
     * @return имя шаблона выбора студента
     */
    @GetMapping("/new_booking/select_student/{bookId}")
    public String selectStudent(@PathVariable Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        model.addAttribute("students", studentService.getAllStudents());
        return "select_student";
    }

    /**
     * Отображает страницу подтверждения бронирования
     * @param bookId ID книги
     * @param studentId ID студента
     * @param model модель для передачи данных в представление
     * @return имя шаблона подтверждения бронирования
     */
    @GetMapping("/new_booking/confirm/{bookId}/{studentId}")
    public String confirmBooking(@PathVariable Long bookId,
                                 @PathVariable Long studentId,
                                 Model model) {
        model.addAttribute("book", bookService.findBookById(bookId));
        model.addAttribute("student", studentService.findStudentById(studentId));
        return "confirm_booking";
    }

    /**
     * Обрабатывает создание нового бронирования
     * @param bookId ID книги
     * @param studentId ID студента
     * @return перенаправление на главную страницу
     */
    @PostMapping("/new_booking/confirm")
    public String processBooking(@RequestParam Long bookId,
                                 @RequestParam Long studentId) {
        currentBookingService.createBooking(bookId, studentId);
        return "redirect:/";
    }
}