package com.inventiv.case_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventiv.case_api.entity.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

}