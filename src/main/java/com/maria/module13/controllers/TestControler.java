package com.maria.module13.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestControler {
    @GetMapping(value = "/test")
    public ModelAndView helloWorld() {
        ModelAndView result = new ModelAndView("test");
        return result;
    }
}
