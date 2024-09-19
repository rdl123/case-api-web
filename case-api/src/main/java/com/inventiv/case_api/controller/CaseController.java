package com.inventiv.case_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.exception.CaseCreationException;
import com.inventiv.case_api.exception.CaseNotFoundException;
import com.inventiv.case_api.service.CaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @PostMapping
    @Operation(summary = "Create a new case", description = "Creates a new case with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Case created successfully", content = @Content(schema = @Schema(implementation = CaseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CaseDTO> createCase(@RequestBody CaseDTO caseDTO) {
        try {
            CaseDTO createdCase = caseService.createCase(caseDTO);
            return new ResponseEntity<>(createdCase, HttpStatus.CREATED);
        } catch (CaseCreationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a case by ID", description = "Retrieves the case details for the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Case found", content = @Content(schema = @Schema(implementation = CaseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Case not found")
    })
    public ResponseEntity<CaseDTO> getCase(@PathVariable("id") Long caseId) {
        try {
            CaseDTO caseDTO = caseService.getCaseById(caseId);
            return ResponseEntity.ok(caseDTO);
        } catch (CaseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing case", description = "Updates the case with the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Case updated successfully", content = @Content(schema = @Schema(implementation = CaseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Case not found")
    })
    public ResponseEntity<CaseDTO> updateCase(@PathVariable("id") Long caseId, @RequestBody CaseDTO caseDTO) {
        try {
            CaseDTO updatedCase = caseService.updateCase(caseId, caseDTO);
            return ResponseEntity.ok(updatedCase);
        } catch (CaseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a case", description = "Deletes the case with the given ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Case deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Case not found")
    })
    public ResponseEntity<Void> deleteCase(@PathVariable("id") Long caseId) {
        try {
            caseService.deleteCase(caseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CaseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
