package com.inventiv.case_api.exception;

public class CaseNotFoundException extends RuntimeException {
	
    public CaseNotFoundException(Long caseId) {
        super("Case not found with id: " + caseId);
    }
}

