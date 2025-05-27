package com.diningreview.dining.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Hibernate is the tool that Spring Boot uses to manage database schema based on java entity classes
@RestController
@RequestMapping("/dining")
public class DiningController {


    @GetMapping()
    public String getDiners() {
        return "Hello World!";
    }

    @PutMapping()
    public void createNewDinner() {
        
    }
    
    // record Dinner {
    //     public Dinner() {

    //     }
    // }
    
}
