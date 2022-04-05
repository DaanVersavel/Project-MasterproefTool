package com.example.MasterproofTool.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
