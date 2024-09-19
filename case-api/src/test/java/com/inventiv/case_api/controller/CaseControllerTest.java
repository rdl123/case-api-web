package com.inventiv.case_api.controller;

import com.inventiv.case_api.dto.CaseDTO;
import com.inventiv.case_api.service.CaseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CaseController.class)
public class CaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaseService caseService;

    @Test
    void testGetCaseById() throws Exception {
        // Setup
        CaseDTO caseDTO = new CaseDTO(1L, LocalDateTime.now(), LocalDateTime.now(), "Title", "Description");
        when(caseService.getCaseById(1L)).thenReturn(caseDTO);

        // Execute & Verify
        mockMvc.perform(get("/cases/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.description").value("Description"));
    }
}
