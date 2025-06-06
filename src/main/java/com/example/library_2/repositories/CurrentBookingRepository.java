package com.example.library_2.repositories;

import com.example.library_2.models.CurrentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с текущими бронированиями книг.
 * Наследует стандартные CRUD-методы из JpaRepository.
 */
public interface CurrentBookingRepository extends JpaRepository<CurrentBooking, Long> {
    /**
     * Поиск текущих бронирований по ключевому слову.
     * Ищет совпадения в названии книги, авторе и ФИО студента.
     * @param keyword ключевое слово для поиска
     * @return список найденных бронирований
     */
    @Query("select c from CurrentBooking c where concat(c.book.title, c.book.author, c.student.fullName) like %?1%")
    List<CurrentBooking> findCurrentBookingsByKeyword(String keyword);

    /**
     * Подсчет количества бронирований на указанную дату.
     * @param date дата для подсчета
     * @return количество бронирований
     */
    Integer countCurrentBookingsByBookingDate(LocalDate date);
}

