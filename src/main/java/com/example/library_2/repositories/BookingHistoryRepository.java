package com.example.library_2.repositories;

import com.example.library_2.models.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с историей бронирований книг.
 * Наследует стандартные CRUD-методы из JpaRepository.
 */
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {
    /**
     * Поиск в истории бронирований по ключевому слову.
     * Ищет совпадения в названии книги, авторе и ФИО студента.
     * @param keyword ключевое слово для поиска
     * @return список найденных записей истории
     */
    @Query("select h from BookingHistory h where concat(h.book.title, h.book.author, h.student.fullName) like %?1%")
    List<BookingHistory> findBookingsHistoriesByKeyword(String keyword);

    /**
     * Подсчет количества бронирований по дате выдачи.
     * @param bookingDate дата выдачи для подсчета
     * @return количество бронирований
     */
    Integer countBookingsByBookingDate(LocalDate bookingDate);
}
