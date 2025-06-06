package com.example.library_2.services;

import com.example.library_2.models.Student;
import com.example.library_2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы со студентами.
 * Содержит бизнес-логику управления студентами.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Получить список всех студентов
     * @return список всех студентов
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Поиск студентов по ключевому слову
     * @param keyword ключевое слово для поиска
     * @return список найденных студентов или все студенты если ключевое слово пустое
     */
    public List<Student> findStudentsByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return studentRepository.findAll();
        }
        return studentRepository.findStudentsByKeyword(keyword);
    }

    /**
     * Сохранить студента (создание или обновление)
     * @param student объект студента для сохранения
     */
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    /**
     * Удалить студента по ID
     * @param id идентификатор студента для удаления
     */
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    /**
     * Найти студента по ID
     * @param id идентификатор студента
     * @return найденный студент
     */
    public Student findStudentById(long id) {
        return studentRepository.findById(id).get();
    }
}
