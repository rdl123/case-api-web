package com.inventiv.case_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.entity.Case;

@Mapper(componentModel = "spring")
public interface CaseMapper {
    CaseMapper INSTANCE = Mappers.getMapper(CaseMapper.class);

    CaseDTO toDTO(Case caseEntity);

    Case toEntity(CaseDTO caseDTO);
}