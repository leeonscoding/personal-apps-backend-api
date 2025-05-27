package com.leeonscoding.personal_apps.loan;

import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import com.leeonscoding.personal_apps.services.loan.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceUnitTests {

    @Autowired
    private LoanServiceImpl loanService;

    Loan newLoan = null;

    @BeforeEach
    void setup() {
        LoanDTO loanDTO = new LoanDTO(0, "kosai", 7000, null, CategoryType.TAKE, StatusType.UNPAID);

        newLoan = loanService.createLoan(loanDTO);
    }

    @Test
    void createLoanTest() {
        assertEquals("kosai", newLoan.getName());
        assertEquals(7000, newLoan.getAmount());
    }

    @Test
    void getLoanByIdTest() {
        LoanDTO loan = loanService.getLoanById(newLoan.getId());

        assertEquals(newLoan.getName(), loan.name());
    }
}
