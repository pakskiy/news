package com.pakskiy.news.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_news_sequence")
    @SequenceGenerator(name = "id_news_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "body")
    private String body;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private NewsStatus newsStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
