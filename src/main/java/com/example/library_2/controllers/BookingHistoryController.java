package com.example.library_2.controllers;

import com.example.library_2.services.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Контроллер для работы с историей бронирований.
 * Обрабатывает запросы, связанные с архивом выданных книг.
 */
@Controller
public class BookingHistoryController {
    @Autowired
    BookingHistoryService bookingHistoryService;

    /**
     * Отображает историю бронирований
     * @param keyword ключевое слово для поиска (опционально)
     * @param model модель для передачи данных в представление
     * @return имя шаблона истории бронирований
     */
    @GetMapping("/booking_history")
    public String listBookingHistory(@RequestParam(required = false, name = "keyword") String keyword, Model model) {
        model.addAttribute("booking_history", bookingHistoryService.findBookingHistoriesByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "booking_history";
    }

    /**
     * Обрабатывает поиск в истории бронирований
     * @param keyword ключевое слово для поиска
     * @return перенаправление на страницу с результатами поиска
     */
    @PostMapping("/booking_history/search")
    public String searchBookingHistory(@RequestParam(required = false, name = "keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "redirect:/booking_history";
        }
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "redirect:/booking_history?keyword=" + encodedKeyword;
    }

    /**
     * Сбрасывает результаты поиска
     * @return перенаправление на полную историю бронирований
     */
    @PostMapping("/booking_history/reset")
    public String resetBookingHistory() {
        return "redirect:/booking_history";
    }
}
