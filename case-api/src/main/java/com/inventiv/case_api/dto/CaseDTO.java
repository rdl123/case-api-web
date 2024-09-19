package com.inventiv.case_api.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseDTO {
    private Long caseId;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private String title;
    private String description;
}
