package com.project.cafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "helloasdftest";
    }

    @GetMapping("/test")
    public String test() {
        return "testasdfasdfaaaaaaaaaaaasdf";
    }
}
