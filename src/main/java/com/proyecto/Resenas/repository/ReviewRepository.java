package com.proyecto.Resenas.repository;

import com.proyecto.Resenas.model.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Long> {
    Flux<Review> findAllByOrderByCreatedAtDesc();
}