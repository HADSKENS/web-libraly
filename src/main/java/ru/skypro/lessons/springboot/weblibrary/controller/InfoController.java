package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

public class InfoController{
    @Value("${app.env}")
    private String appInfo;
    @GetMapping("/appInfo")
    public String appInfo(){
        return appInfo;
    }
}
