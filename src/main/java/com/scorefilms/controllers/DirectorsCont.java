package com.scorefilms.controllers;

import com.scorefilms.controllers.Main.Attributes;
import com.scorefilms.models.Directors;
import com.scorefilms.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/directors")
public class DirectorsCont extends Attributes {

    @GetMapping
    public String directors(Model model) {
        model.addAttribute("directors", repoDirectors.findAll());
        model.addAttribute("role", getRole());
        return "directors";
    }

    @GetMapping("/{id}")
    public String director(Model model, @PathVariable Long id) {
        model.addAttribute("director", repoDirectors.getReferenceById(id));
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
        return "director";
    }

    @GetMapping("/add")
    public String director_add(Model model) {
        AddAttributes(model);
        return "director_add";
    }

    @PostMapping("/add")
    public String director_add(Model model, @RequestParam String name, @RequestParam MultipartFile poster, @RequestParam int birthday, @RequestParam int die, @RequestParam String description) {
        try {
            String result_poster = "";
            String uuidFile = UUID.randomUUID().toString();
            if (poster != null && !Objects.requireNonNull(poster.getOriginalFilename()).isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                result_poster = uuidFile + "_" + poster.getOriginalFilename();
                poster.transferTo(new File(uploadPath + "/" + result_poster));
            }
            Users user = getUser();
            user.addDirector(new Directors(name, birthday, die, description, result_poster));
            repoUsers.save(user);
        } catch (IOException e) {
            AddAttributes(model);
            model.addAttribute("message", "Некорректные данные!");
            return "director_add";
        }
        return "redirect:/directors";
    }

    @GetMapping("/edit/{id}")
    public String director_edit(@PathVariable Long id, Model model) {
        if (!repoDirectors.existsById(id)) return "redirect:/directors";
        AddAttributesDirectorEdit(model, id);
        return "director_edit";
    }

    @PostMapping("/edit/{id}")
    public String director_edit(Model model, @PathVariable Long id, @RequestParam String name, @RequestParam MultipartFile poster, @RequestParam int birthday, @RequestParam int die, @RequestParam String description) {
        try {
            Directors directors = repoDirectors.getReferenceById(id);

            directors.setName(name);
            directors.setBirthday(birthday);
            directors.setDie(die);
            directors.setDescription(description);

            String uuidFile = UUID.randomUUID().toString();
            if (poster != null && !Objects.requireNonNull(poster.getOriginalFilename()).isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result_poster = uuidFile + "_" + poster.getOriginalFilename();
                poster.transferTo(new File(uploadPath + "/" + result_poster));
                directors.setPoster(result_poster);
            }

            repoDirectors.save(directors);
        } catch (Exception e) {
            AddAttributesDirectorEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "director_edit";
        }
        return "redirect:/directors";
    }

    @GetMapping("/delete/{id}")
    public String director_delete(@PathVariable Long id) {
        repoDirectors.deleteById(id);
        return "redirect:/directors";
    }
}
