package com.idorasi.htmlserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/login")
    public String login() {
        log.info("Generating login page");
        return "login";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String username, Model model) {
        log.info("Received login request, generating html for user: " + username);
        model.addAttribute("username", username);
        return "hello";
    }



}
