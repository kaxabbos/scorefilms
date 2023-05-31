package com.scorefilms.controllers.Main;

import com.scorefilms.models.Users;
import com.scorefilms.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.DecimalFormat;

public class Main {
    @Autowired
    protected RepoUsers repoUsers;
    @Autowired
    protected RepoCarts repoCarts;
    @Autowired
    protected RepoSessions repoSessions;
    @Autowired
    protected RepoComments repoComments;
    @Autowired
    protected RepoDirectors repoDirectors;
    @Value("${upload.path}")
    protected String uploadPath;
    protected DecimalFormat decimalFormat = new DecimalFormat("#.##");
    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return repoUsers.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }
}
