package com.proyecto.Resenas.controller;

import com.proyecto.Resenas.model.Review;
import com.proyecto.Resenas.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // NUEVO MÉTODO AÑADIDO: Atrapa la redirección del login
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("reviews", reviewService.getAllHistory());
        return "dashboard";
    }

    // --- API REST (Lógica) ---
    @PostMapping("/api/analyze")
    @ResponseBody
    public Mono<Review> analyzeReviewAPI(@RequestBody String text) {
        return reviewService.analyzeAndSave(text);
    }

    @GetMapping("/simulador")
    public String showSimulator() {
        return "simulador";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}