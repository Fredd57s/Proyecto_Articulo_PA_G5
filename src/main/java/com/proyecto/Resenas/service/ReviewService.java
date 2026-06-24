package com.proyecto.Resenas.service;

import com.proyecto.Resenas.dto.AnalysisResponse;
import com.proyecto.Resenas.model.Review;
import com.proyecto.Resenas.repository.ReviewRepository;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReviewService {

    private final ReviewAnalyzerAgent agent;
    private final ReviewRepository repository;

    public ReviewService(ChatLanguageModel chatLanguageModel, ReviewRepository repository) {
        this.agent = AiServices.create(ReviewAnalyzerAgent.class, chatLanguageModel);
        this.repository = repository;
    }

    public Mono<Review> analyzeAndSave(String reviewText) {

        // 1. Llamamos a Gemini (Envuelto en fromCallable y boundedElastic para NO bloquear Netty)
        return Mono.fromCallable(() -> agent.analyzeReview(reviewText))
                .subscribeOn(Schedulers.boundedElastic()) // <- ¡El secreto para integrar APIs bloqueantes!
                .flatMap(response -> {
                    // 2. Creamos la entidad con la respuesta REAL de la IA
                    Review review = new Review();
                    review.setText(reviewText);
                    review.setAuthorType(response.authorType());
                    review.setSentiment(response.sentiment());
                    review.setAnalysisJustification(response.justification());

                    // 3. Guardamos en la base de datos de forma asíncrona
                    return repository.save(review);
                });
    }

    public Flux<Review> getAllHistory() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}