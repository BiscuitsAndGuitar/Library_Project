package com.example.library_2.controllers;

import com.example.library_2.models.BookingHistory;
import com.example.library_2.models.CurrentBooking;
import com.example.library_2.services.BookService;
import com.example.library_2.services.BookingHistoryService;
import com.example.library_2.services.CurrentBookingService;
import com.example.library_2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Контроллер для возврата книг.
 * Обрабатывает процесс возврата книг в библиотеку.
 */
@Controller
public class ReturnController {
    @Autowired
    private CurrentBookingService currentBookingService;

    @Autowired
    private BookingHistoryService bookingHistoryService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    /**
     * Отображает страницу подтверждения возврата
     * @param bookingId ID бронирования
     * @param model модель для передачи данных в представление
     * @return имя шаблона подтверждения возврата
     */
    @GetMapping("/confirm_return/{bookingId}")
    public String confirmReturn(@PathVariable Long bookingId, Model model) {
        CurrentBooking booking = currentBookingService.findById(bookingId);
        model.addAttribute("booking", booking);
        return "confirm_return";
    }

    /**
     * Обрабатывает возврат книги
     * @param bookingId ID возвращаемого бронирования
     * @return перенаправление на главную страницу
     */
    @PostMapping("/process_return")
    public String processReturn(@RequestParam Long bookingId) {
        CurrentBooking booking = currentBookingService.findById(bookingId);

        // Создаем запись в истории
        BookingHistory history = new BookingHistory();
        history.setBook(booking.getBook());
        history.setStudent(booking.getStudent());
        history.setBookingDate(booking.getBookingDate());
        history.setReturnDate(LocalDate.now());

        bookingHistoryService.save(history);

        // Удаляем текущее бронирование
        currentBookingService.delete(booking);

        return "redirect:/";
    }
}
