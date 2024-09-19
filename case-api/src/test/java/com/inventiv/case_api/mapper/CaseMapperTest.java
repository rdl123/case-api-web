package com.inventiv.case_api.mapper;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.entity.Case;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class CaseMapperTest {

    private CaseMapper caseMapper = Mappers.getMapper(CaseMapper.class);

    @Test
    void testCaseToCaseDTO() {
        // Setup
        Case caseEntity = new Case(1L, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description");

        // Execute
        CaseDTO caseDTO = caseMapper.toDTO(caseEntity);

        // Verify
        assertEquals(1L, caseDTO.getCaseId());
        assertEquals("Title", caseDTO.getTitle());
        assertEquals("Description", caseDTO.getDescription());
    }
}
