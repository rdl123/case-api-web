package com.inventiv.case_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdateDate;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 2056)
    private String description;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
    }
}