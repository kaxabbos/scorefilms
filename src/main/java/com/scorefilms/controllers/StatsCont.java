package com.scorefilms.controllers;

import com.scorefilms.controllers.Main.Attributes;
import com.scorefilms.models.Directors;
import com.scorefilms.models.Sessions;
import com.scorefilms.models.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class StatsCont extends Attributes {

    @GetMapping("/stats")
    public String sales(Model model) {
        List<Directors> directorsList = getUser().getDirectors();
        List<Sessions> sessions = new ArrayList<>();
        for (Directors d : directorsList) {
            sessions.addAll(d.getSessions());
        }

        int[] genre = new int[Genre.values().length];
        List<Genre> genreList = Arrays.stream(Genre.values()).toList();
        int income = 0;
        for (Sessions session : sessions) {
            income += session.getCount();
            int index = 0;
            for (Genre i : genreList) {
                if (i == session.getGenre()) {
                    genre[index] += session.getCount();
                }
                index++;
            }
        }

        model.addAttribute("income", income);
        model.addAttribute("genre", genre);
        model.addAttribute("sessions", sessions);
        model.addAttribute("role", getRole());

        sessions.sort(Comparator.comparing(Sessions::getCount));
        Collections.reverse(sessions);

        String[] topName = new String[5];
        int[] topNum = new int[5];

        for (int i = 0; i < sessions.size(); i++) {
            if (i == 5) break;
            topName[i] = sessions.get(i).getName();
            topNum[i] = sessions.get(i).getCount();
        }
        model.addAttribute("topName", topName);
        model.addAttribute("topNum", topNum);

        return "stats";
    }
}
