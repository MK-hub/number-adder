package com.example.numberadder.controller;

import com.example.numberadder.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class NumbersController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/database")
    public int getNumber() {
        return applicationService.getNumberFromDataBase();
    }

    @GetMapping("/adding")
    public String getSum() {
        return applicationService.addingNumbers(1, 20);
    }
}
