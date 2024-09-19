package com.inventiv.case_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.entity.Case;
import com.inventiv.case_api.exception.CaseCreationException;
import com.inventiv.case_api.exception.CaseNotFoundException;
import com.inventiv.case_api.mapper.CaseMapper;
import com.inventiv.case_api.repository.CaseRepository;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CaseMapper caseMapper;

    // Create
    public CaseDTO createCase(CaseDTO caseDTO) {
        try {
            Case caseEntity = caseMapper.toEntity(caseDTO);
            Case savedCase = caseRepository.save(caseEntity);
            return caseMapper.toDTO(savedCase);
        } catch (Exception e) {
            throw new CaseCreationException("Failed to create case");
        }
    }

    // Read
    public CaseDTO getCaseById(Long caseId) {
        return caseRepository.findById(caseId)
                .map(caseMapper::toDTO)
                .orElseThrow(() -> new CaseNotFoundException(caseId));
    }

    // Update
    public CaseDTO updateCase(Long caseId, CaseDTO caseDTO) {
        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException(caseId));

        existingCase.setTitle(caseDTO.getTitle());
        existingCase.setDescription(caseDTO.getDescription());
        Case updatedCase = caseRepository.save(existingCase);

        return caseMapper.toDTO(updatedCase);
    }

    // Delete
    public void deleteCase(Long caseId) {
        Case existingCase = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException(caseId));

        caseRepository.delete(existingCase);
    }
}
