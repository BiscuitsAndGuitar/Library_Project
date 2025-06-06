package com.example.library_2.repositories;

import com.example.library_2.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
        * Репозиторий для работы с книгами в базе данных.
        * Наследует стандартные CRUD-методы из JpaRepository.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Поиск книг по ключевому слову.
     * Ищет совпадения в названии, авторе, годе издания и издателе.
     * @param keyword ключевое слово для поиска
     * @return список найденных книг
     */
    @Query("select b from Book b where concat(b.title, b.author, b.publicationYear, b.publisher) like %?1%")
    List<Book> findBooksByKeyword(String keyword);
}
