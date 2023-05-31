package com.scorefilms.controllers;

import com.scorefilms.controllers.Main.Attributes;
import com.scorefilms.models.Sessions;
import com.scorefilms.models.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogCont extends Attributes {
    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("sessions", repoSessions.findAll());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("role", getRole());
        return "catalog";
    }

    @GetMapping("/catalog/all")
    public String catalog_main(Model model) {
        model.addAttribute("sessions", repoSessions.findAll());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("role", getRole());
        return "catalog";
    }

    @PostMapping("/catalog/search")
    public String search(@RequestParam Genre genre, @RequestParam String date_range, Model model) {
        String[] date = date_range.split(" ");

        int with = Integer.parseInt(date[0]);
        int before = Integer.parseInt(date[2]);

        List<Sessions> temp = repoSessions.findAllByGenre(genre);
        List<Sessions> sessions = temp.stream().filter(session -> (with <= session.getYear() && session.getYear() <= before)).toList();

        model.addAttribute("sessions", sessions);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("role", getRole());
        return "catalog";
    }

    @GetMapping("/catalog/genre/{genre}")
    public String catalog_genre_search(@PathVariable(value = "genre") Genre genre, Model model) {
        model.addAttribute("sessions", repoSessions.findAllByGenre(genre));
        model.addAttribute("genres", Genre.values());
        model.addAttribute("role", getRole());
        return "catalog";
    }

    @GetMapping("/catalog/year/{year}")
    public String catalog_year_search(@PathVariable(value = "year") int year, Model model) {
        model.addAttribute("sessions", repoSessions.findAllByYear(year));
        model.addAttribute("role", getRole());
        return "catalog";
    }

    @PostMapping("/catalog/search/name")
    public String catalogSearch(@RequestParam String search, Model model) {
        model.addAttribute("sessions", repoSessions.findAllByNameContaining(search));
        model.addAttribute("genres", Genre.values());
        model.addAttribute("role", getRole());
        return "catalog";
    }
}
