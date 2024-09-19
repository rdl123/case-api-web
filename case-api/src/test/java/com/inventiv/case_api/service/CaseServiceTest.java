package com.inventiv.case_api.service;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.entity.Case;
import com.inventiv.case_api.mapper.CaseMapper;
import com.inventiv.case_api.repository.CaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CaseServiceTest {

    @InjectMocks
    private CaseService caseService;

    @Mock
    private CaseRepository caseRepository;

    @Mock
    private CaseMapper caseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCaseById() {
        // Setup
        Case caseEntity = new Case(1L, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description");
        CaseDTO caseDTO = new CaseDTO(1L, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description");
        when(caseRepository.findById(1L)).thenReturn(Optional.of(caseEntity));
        when(caseMapper.toDTO(caseEntity)).thenReturn(caseDTO);

        // Execute
        CaseDTO result = caseService.getCaseById(1L);

        // Verify
        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
    }
}
