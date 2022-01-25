package com.spring.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // basic controller

    @GetMapping(value = "/appCheck")
    public String getDemo(){

        return "Application Running!!!";

    }

}
