package com.example.library_2.controllers;

import com.example.library_2.services.CurrentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Контроллер для работы с текущими бронированиями.
 * Обрабатывает запросы, связанные с активными выдачами книг.
 */
@Controller
public class CurrentBookingController {
    @Autowired
    private CurrentBookingService currentBookingService;

    /**
     * Отображает список текущих бронирований
     * @param keyword ключевое слово для поиска (опционально)
     * @param model модель для передачи данных в представление
     * @return имя шаблона текущих бронирований
     */
    @GetMapping("/current_bookings")
    public String listCurrentBookings(@RequestParam(required = false, name = "keyword") String keyword, Model model) {
        model.addAttribute("current_bookings", currentBookingService.findCurrentBookingsByKeyword(keyword));
        model.addAttribute("keyword", keyword);
        return "current_bookings";
    }

    /**
     * Обрабатывает поиск текущих бронирований
     * @param keyword ключевое слово для поиска
     * @return перенаправление на страницу с результатами поиска
     */
    @PostMapping("/current_bookings/search")
    public String searchStudents(@RequestParam(required = false, name = "keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "redirect:/current_bookings";
        }
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        return "redirect:/current_bookings?keyword=" + encodedKeyword;
    }

    /**
     * Сбрасывает результаты поиска
     * @return перенаправление на полный список текущих бронирований
     */
    @PostMapping("/current_bookings/reset")
    public String resetSearch() {
        return "redirect:/current_bookings";
    }
}

