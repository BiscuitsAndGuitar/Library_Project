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
@Table(name = "books", schema = "library_2")
public class Book {
    // Первичный ключ с автоинкрементом
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Название книги (обязательное поле, макс. длина 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    // Автор книги (обязательное поле)
    @Column(name = "author", nullable = false, length = 100)
    private String author;

    // Год публикации (не обязательное поле)
    @Column(name = "publication_year")
    private Integer publicationYear;

    // Издательство (макс. длина 100)
    @Column(name = "publisher", length = 100)
    private String publisher;

    // Связь "один-ко-многим" с историей бронирований
    @OneToMany(mappedBy = "book")
    private Set<BookingHistory> bookingsHistories = new LinkedHashSet<>();

    // Связь "один-ко-многим" с активными бронированиями
    @OneToMany(mappedBy = "book")
    private Set<CurrentBooking> currentBookings = new LinkedHashSet<>();
}
