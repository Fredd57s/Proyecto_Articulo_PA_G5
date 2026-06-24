package com.proyecto.Resenas.repository;

import com.proyecto.Resenas.model.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Long> {
    // Al devolver Flux, MySQL enviará las reseñas como un "río de datos" continuo
    Flux<Review> findAllByOrderByCreatedAtDesc();
}