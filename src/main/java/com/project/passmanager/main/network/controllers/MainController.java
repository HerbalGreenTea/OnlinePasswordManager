package com.project.passmanager.main.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("main")
    public String getMainPage() {
        return "mainPage";
    }
}
