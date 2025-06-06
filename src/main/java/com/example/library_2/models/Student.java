package com.example.library_2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

// Аннотации Lombok для автоматической генерации геттеров и сеттеров
@Getter
@Setter
// Указывает, что это JPA сущность
@Entity
// Задает имя таблицы и схему в БД
@Table(name = "students", schema = "library_2")
public class Student {
    // Первичный ключ с автоинкрементом
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Полное имя студента (обязательное поле)
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    // Номер студенческого билета (уникальный, обязательный)
    @Column(name = "student_card_number", nullable = false, length = 20)
    private String studentCardNumber;

    // История бронирований студента
    @OneToMany(mappedBy = "student")
    private Set<BookingHistory> bookingsHistories = new LinkedHashSet<>();

    // Текущие бронирования студента
    @OneToMany(mappedBy = "student")
    private Set<CurrentBooking> currentBookings = new LinkedHashSet<>();
}