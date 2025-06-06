package com.example.library_2.repositories;

import com.example.library_2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Репозиторий для работы со студентами.
 * Наследует стандартные CRUD-методы из JpaRepository.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * Поиск студентов по ключевому слову.
     * Ищет совпадения в ФИО и номере студенческого билета.
     * @param keyword ключевое слово для поиска
     * @return список найденных студентов
     */
    @Query("select s from Student s where concat(s.fullName, s.studentCardNumber) like %?1%")
    List<Student> findStudentsByKeyword(String keyword);
}
