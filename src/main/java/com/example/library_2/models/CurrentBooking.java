package com.example.library_2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

// Аннотации Lombok для автоматической генерации геттеров и сеттеров
@Getter
@Setter
// Указывает, что это JPA сущность
@Entity
// Задает имя таблицы и схему в БД
@Table(name = "current_bookings", schema = "library_2")
public class CurrentBooking {
    // Первичный ключ с автоинкрементом
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Связь с книгой
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Связь со студентом
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Дата выдачи с дефолтным значением текущей даты
    @ColumnDefault("(curdate())")
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;
}