package com.proyecto.Resenas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Table("reviews_history")
@Data
public class Review {

    @Id
    private Long id;

    @Column("text")
    private String text;

    @Column("author_type")
    private String authorType;

    @Column("sentiment")
    private String sentiment;

    @Column("analysis_justification")
    private String analysisJustification;

    @Column("created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // Forma reactiva de auto-asignar la fecha
}