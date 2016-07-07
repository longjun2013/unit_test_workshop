package com.longjun.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/trigger")
    String execute() {
        return "已触发任务执行.";
    }
}
