package com.leeonscoding.personal_apps.loan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leeonscoding.personal_apps.controllers.loan.LoanController;
import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerUnitTest {

    @Autowired
    private LoanController loanController;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
        assertThat(loanController).isNotNull();
    }

    @Test
    void postTest() throws Exception {
        LoanDTO loanDTO = new LoanDTO(0, "kosai", 7000, null, CategoryType.TAKE, StatusType.UNPAID);

        mockMvc.perform(
                    post("/loans")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(loanDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value( loanDTO.name()));
    }
}
