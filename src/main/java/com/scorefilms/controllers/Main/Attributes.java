package com.scorefilms.controllers.Main;

import org.springframework.ui.Model;

public class Attributes extends Main {
    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesDirectorEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("director", repoDirectors.getReferenceById(id));
    }

}
