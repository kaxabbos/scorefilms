package com.scorefilms.controllers;

import com.scorefilms.controllers.Main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartCont extends Attributes {
    @GetMapping
    public String cart(Model model) {
        model.addAttribute("user", getUser());
        model.addAttribute("role", getRole());
        return "cart";
    }

    @GetMapping("/delete/{id}")
    public String cartDelete(@PathVariable Long id) {
        repoCarts.deleteById(id);
        return "redirect:/cart";
    }
}
