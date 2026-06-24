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

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // En WebFlux, le pasamos el Flux directamente al modelo.
        // Thymeleaf está adaptado para suscribirse automáticamente sin bloquear.
        model.addAttribute("reviews", reviewService.getAllHistory());
        return "dashboard";
    }

    // --- API REST (Lógica) ---
    @PostMapping("/api/analyze")
    @ResponseBody
    public Mono<Review> analyzeReviewAPI(@RequestBody String text) {
        // Retornamos el Mono directamente. Netty lo manejará en su Event Loop.
        return reviewService.analyzeAndSave(text);
    }

    @GetMapping("/simulador")
    public String showSimulator() {
        return "simulador";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Llama al archivo login.html
    }
}