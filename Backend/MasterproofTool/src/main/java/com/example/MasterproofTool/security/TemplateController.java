package com.example.MasterproofTool.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TemplateController {

    @GetMapping("/login")
    @CrossOrigin(origins = "*")
    public String getLogin() {
        return "login";
    }
}
