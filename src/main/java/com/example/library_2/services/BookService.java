package com.example.library_2.services;

import com.example.library_2.models.Book;
import com.example.library_2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
        * Сервис для работы с книгами.
 * Инкапсулирует бизнес-логику операций с книгами.
        */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * Получить список всех книг
     * @return список всех книг в системе
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Поиск книг по ключевому слову
     * @param keyword ключевое слово для поиска
     * @return список найденных книг или все книги если ключевое слово пустое
     */
    public List<Book> findBooksByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.findBooksByKeyword(keyword);
    }

    /**
     * Сохранить книгу (создание или обновление)
     * @param book объект книги для сохранения
     */
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Удалить книгу по ID
     * @param id идентификатор книги для удаления
     */
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Найти книгу по ID
     * @param id идентификатор книги
     * @return найденная книга
     */
    public Book findBookById(long id) {
        return bookRepository.findById(id).get();
    }
}
