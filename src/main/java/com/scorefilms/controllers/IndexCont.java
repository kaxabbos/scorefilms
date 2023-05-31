package com.scorefilms.controllers;

import com.scorefilms.controllers.Main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Attributes {

    @GetMapping("/about")
    public String about(Model model) {
        AddAttributes(model);
        return "about";
    }

    @GetMapping
    public String index1() {
        return "redirect:/catalog/all";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/catalog/all";
    }
}