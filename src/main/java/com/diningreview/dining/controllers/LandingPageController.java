package com.diningreview.dining.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LandingPageController {

    @GetMapping
    public String greeting() {
        return "Hello World!";
    }

}
