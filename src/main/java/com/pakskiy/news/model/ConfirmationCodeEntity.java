package com.pakskiy.news.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "confirmation_code")
public class ConfirmationCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_conf_code_sequence")
    @SequenceGenerator(name = "id_conf_code_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "code")
    private String code;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "status")
    private int status;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}