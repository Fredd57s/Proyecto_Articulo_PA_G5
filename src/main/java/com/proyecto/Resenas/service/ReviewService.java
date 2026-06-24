package com.proyecto.Resenas.service;

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

        return Mono.fromCallable(() -> agent.analyzeReview(reviewText))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(response -> {
                    Review review = new Review();
                    review.setText(reviewText);
                    review.setAuthorType(response.authorType());
                    review.setSentiment(response.sentiment());
                    review.setAnalysisJustification(response.justification());

                    return repository.save(review);
                });
    }

    public Flux<Review> getAllHistory() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
}