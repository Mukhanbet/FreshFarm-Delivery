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

    @GetMapping("/home")
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

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "blog";
    }

    @GetMapping("/blog-details")
    public String blogDetails(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "blog-details";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "checkout";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "contact";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "main";
    }

    @GetMapping("/shop-details")
    public String shopDetails(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shop-details";
    }

    @GetMapping("/shop-grid")
    public String shopGrid(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shop-grid";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shoping-cart";
    }
}
