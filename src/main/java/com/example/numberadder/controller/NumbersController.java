package com.example.numberadder.controller;

import com.example.numberadder.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class NumbersController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/adding")
    public String getSum(@RequestParam(value = "minValue") int minValue,
                         @RequestParam(value = "maxValue") int maxValue) {
        return applicationService.addingNumbers(minValue, maxValue);
    }
}
