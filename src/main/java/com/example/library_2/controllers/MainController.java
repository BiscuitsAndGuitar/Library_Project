package com.example.library_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Основной контроллер приложения.
 * Обрабатывает запросы к главной странице.
 */
@Controller
public class MainController {
    /**
     * Отображает главную страницу
     * @return имя шаблона главной страницы
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
