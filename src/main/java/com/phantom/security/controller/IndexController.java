package com.phantom.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author lei.tan
 * @version 1.0
 * @date 2023/1/10 16:01
 */
@Slf4j
@RestController
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello...";
    }

}
