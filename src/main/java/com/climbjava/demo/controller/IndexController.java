package com.climbjava.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @GetMapping("/")
    public String Hello() {
        return "index";
    }
    
}
