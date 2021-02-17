package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class deController {

    @GetMapping("/api/register1")
    @ResponseBody
    public String getlog1(String name){
        System.out.println(name);
        return  "df";
    }
}

