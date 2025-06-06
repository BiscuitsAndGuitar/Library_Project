package com.example.library_2.services;

import com.example.library_2.models.CurrentBooking;
import com.example.library_2.repositories.BookRepository;
import com.example.library_2.repositories.CurrentBookingRepository;
import com.example.library_2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с текущими бронированиями книг.
 * Управляет операциями с активными выдачами книг.
 */
@Service
public class CurrentBookingService {
    @Autowired
    CurrentBookingRepository currentBookingRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    /**
     * Поиск текущих бронирований по ключевому слову
     * @param keyword ключевое слово для поиска
     * @return список найденных бронирований или все бронирования если ключевое слово пустое
     */
    public List<CurrentBooking> findCurrentBookingsByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return currentBookingRepository.findAll();
        }
        return currentBookingRepository.findCurrentBookingsByKeyword(keyword);
    }

    /**
     * Получить все текущие бронирования
     * @return список всех активных бронирований
     */
    public List<CurrentBooking> getAllCurrentBookings() {
        return currentBookingRepository.findAll();
    }

    /**
     * Найти бронирование по ID
     * @param id идентификатор бронирования
     * @return найденное бронирование
     */
    public CurrentBooking findById(Long id) {
        return currentBookingRepository.findById(id).get();
    }

    /**
     * Удалить бронирование
     * @param currentBooking объект бронирования для удаления
     */
    public void delete(CurrentBooking currentBooking) {
        currentBookingRepository.delete(currentBooking);
    }

    /**
     * Создать новое бронирование
     * @param bookId ID книги
     * @param studentId ID студента
     */
    public void createBooking(Long bookId, Long studentId) {
        CurrentBooking booking = new CurrentBooking();
        booking.setBook(bookRepository.findById(bookId).orElseThrow());
        booking.setStudent(studentRepository.findById(studentId).orElseThrow());
        booking.setBookingDate(LocalDate.now());
        currentBookingRepository.save(booking);
    }

    /**
     * Подсчет бронирований по дате выдачи
     * @param date дата для подсчета
     * @return количество бронирований на указанную дату
     */
    public Integer countCurrentBookingsByBookingDate(LocalDate date) {
        return currentBookingRepository.countCurrentBookingsByBookingDate(date);
    }
}
