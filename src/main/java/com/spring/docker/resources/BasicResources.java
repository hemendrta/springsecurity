package com.spring.docker.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker")
public class BasicResources {

    @GetMapping("/welcome")
    public String getDemoResource(){

        return "This is demo url";

    }

}


