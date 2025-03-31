package org.example.sport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

    @GetMapping("/home")
    public String home() {
        return "index"; // Charge index.html
}
}