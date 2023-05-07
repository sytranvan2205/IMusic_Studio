package com.imusicstudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping( "/index" )
    public String index(Model model) {
       model.addAttribute("title","Trang chủ");
        return "index";
    }

}
