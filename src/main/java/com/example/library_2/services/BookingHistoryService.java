package com.example.library_2.services;

import com.example.library_2.models.BookingHistory;
import com.example.library_2.repositories.BookingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с историей бронирований.
 * Управляет архивными данными о выданных книгах.
 */
@Service
public class BookingHistoryService {
    @Autowired
    BookingHistoryRepository bookingHistoryRepository;

    /**
     * Поиск в истории бронирований по ключевому слову
     * @param keyword ключевое слово для поиска
     * @return список найденных записей или вся история если ключевое слово пустое
     */
    public List<BookingHistory> findBookingHistoriesByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return bookingHistoryRepository.findAll();
        }
        return bookingHistoryRepository.findBookingsHistoriesByKeyword(keyword);
    }

    /**
     * Получить всю историю бронирований
     * @return список всех архивных записей
     */
    public List<BookingHistory> getAllBookingHistory() {
        return bookingHistoryRepository.findAll();
    }

    /**
     * Сохранить запись в истории бронирований
     * @param bookingHistory объект записи для сохранения
     */
    public void save(BookingHistory bookingHistory) {
        bookingHistoryRepository.save(bookingHistory);
    }

    /**
     * Подсчет бронирований по дате выдачи
     * @param bookingDate дата выдачи
     * @return количество бронирований на указанную дату
     */
    public Integer countBookingsByBookingDate(LocalDate bookingDate) {
        return bookingHistoryRepository.countBookingsByBookingDate(bookingDate);
    }
}
