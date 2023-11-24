package com.project.passmanager.main.registration.controllers;

import com.project.passmanager.main.registration.models.UserRegistration;
import com.project.passmanager.main.registration.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;
    private final String REGISTRATION_PAGE = "registration";

    /**
     * Метод отправляет шаблон регистрации на клиента, когда обращаемся к регистрации
     */
    @GetMapping("/registration")
    public String registration() {

        return REGISTRATION_PAGE;
    }

    /**
     * Метод сохранения формы регистрации
     */
    @PostMapping("/registration")
    public String adduser(UserRegistration userRegistration, Model model) {
        try {
            userRegistrationService.addUser(userRegistration);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "UserRegistration exists");
            return REGISTRATION_PAGE;
        }
    }

}
