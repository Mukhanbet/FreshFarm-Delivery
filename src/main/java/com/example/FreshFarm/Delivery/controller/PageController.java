package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/pages")
public class PageController {
    public String home(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("authLoginRequest", new AuthLoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("authRegisterRequest", new AuthRegisterRequest());
        return "register";
    }
}
