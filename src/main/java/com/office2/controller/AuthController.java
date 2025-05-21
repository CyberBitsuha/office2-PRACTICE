package com.office2.controller;

import com.office2.model.User;
import com.office2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpSession session,
            HttpServletResponse response,
            Model model
    ) {
        User user = userService.authenticate(login, password);
        if (user == null) {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }

        // Сохраняем пользователя в сессии
        session.setAttribute("loggedUser", user);

        // Добавляем cookie для долгосрочной авторизации ("запомнить меня")
        Cookie remember = new Cookie("rememberMe", user.getUsername());
        remember.setPath("/");
        remember.setMaxAge(60 * 60 * 24 * 365); // год
        response.addCookie(remember);

        return "redirect:/appeals";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // Уничтожаем сессию
        session.invalidate();

        // Удаляем cookie
        Cookie remember = new Cookie("rememberMe", "");
        remember.setPath("/");
        remember.setMaxAge(0);
        response.addCookie(remember);

        return "redirect:/login";
    }
}
