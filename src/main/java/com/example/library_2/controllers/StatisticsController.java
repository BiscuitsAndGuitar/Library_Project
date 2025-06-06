package com.example.library_2.controllers;

import com.example.library_2.repositories.BookingHistoryRepository;
import com.example.library_2.repositories.CurrentBookingRepository;
import com.example.library_2.services.BookingHistoryService;
import com.example.library_2.services.CurrentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.*;
import java.sql.Date;
import java.util.stream.Collectors;

/**
 * Контроллер для работы со статистикой.
 * Обрабатывает запросы, связанные с аналитикой выдачи книг.
 */
@Controller
public class StatisticsController {
    @Autowired
    private BookingHistoryService bookingHistoryService;

    @Autowired
    private CurrentBookingService currentBookingService;

    /**
     * Отображает страницу статистики
     * @return имя шаблона страницы статистики
     */
    @GetMapping("/booking_statistics")
    public String statisticsPage() {
        return "statistics";
    }

    /**
     * Возвращает статистику выдачи книг за последние 30 дней
     * @return список данных статистики в формате JSON
     */
    @ResponseBody
    @GetMapping("/api/book-statistics")
    public List<Map<String, Object>> getBookStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(29);

        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            LocalDate date = startDate.plusDays(i);

            long historyCount = bookingHistoryService.countBookingsByBookingDate(date);
            long currentCount = currentBookingService.countCurrentBookingsByBookingDate(date);
            long total = historyCount + currentCount;

            Map<String, Object> entry = new HashMap<>();
            entry.put("date", date.toString());
            entry.put("count", total);
            result.add(entry);
        }

        return result;
    }
}

