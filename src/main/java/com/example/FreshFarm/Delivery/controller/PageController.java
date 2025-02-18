package com.example.FreshFarm.Delivery.controller;

import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import com.example.FreshFarm.Delivery.service.CommentService;
import com.example.FreshFarm.Delivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/pages")
public class PageController {
    private final ProductService productService;
    private final CommentService commentService;

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
    public String blog(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        List<ProductResponse> products = productService.all(page, size);

        Map<Long, Integer> commentCounts = products.stream()
                .collect(Collectors.toMap(ProductResponse::getId, p -> commentService.getAmountOfComments(p.getId())));

        model.addAttribute("title", "Fresh Farm Delivery");
        model.addAttribute("products", products);
        model.addAttribute("commentCounts", commentCounts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productService.totalPages(page, size));
        model.addAttribute("size", size);

        return "blog";
    }


    @GetMapping("/blog-details/{id}")
    public String blogDetails(Model model, @PathVariable Long id) {
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
    public String shopGrid(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            Model model
    ) {
        model.addAttribute("title", "Fresh Farm Delivery");
        model.addAttribute("discountedProducts", productService.getDiscountedProducts(page, size));
        return "shop-grid";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shoping-cart";
    }
}
