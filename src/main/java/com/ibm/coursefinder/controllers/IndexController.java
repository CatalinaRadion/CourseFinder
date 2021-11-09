package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.seeders.InitialSeeder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

    public IndexController(InitialSeeder seeder) {
        seeder.seed();
    }

    @GetMapping("")
    public String index() {
        return "index";
    }
    @GetMapping("/brewCoffee")
    public ResponseEntity<String> brewCoffee(){
       return ResponseEntity.status(418)
               .header("Teapot","I am not")
               .body("I'm not gonna brew any coffee");
    }
}