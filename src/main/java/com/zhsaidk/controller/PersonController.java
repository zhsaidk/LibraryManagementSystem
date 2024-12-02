package com.zhsaidk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/persons")
public class PersonController {

    public String getAll(){
        return "persons";
    }
}
