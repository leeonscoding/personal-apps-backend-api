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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceUnitTests {

    @Autowired
    private LoanServiceImpl loanService;

    LoanDTO newLoan = null;

    @BeforeEach
    void setup() {
        LoanDTO loanDTO = new LoanDTO(0, "kosai", 7000, null, CategoryType.TAKE, StatusType.UNPAID);

        newLoan = loanService.createLoan(loanDTO);
    }

    @Test
    void createLoanTest() {
        assertEquals("kosai", newLoan.name());
        assertEquals(7000, newLoan.amount());
    }

    @Test
    void getLoanByIdTest() {
        LoanDTO loan = loanService.getLoanById(newLoan.id());

        assertEquals(newLoan.name(), loan.name());
    }

    @Test
    void getAllTest() {
        LoanDTO loanDTO1 = new LoanDTO(0, "mama", 10000, null, CategoryType.GIVE, StatusType.UNPAID);
        LoanDTO loanDTO2 = new LoanDTO(0, "rafi", 2000, null, CategoryType.TAKE, StatusType.UNPAID);
        LoanDTO loanDTO3 = new LoanDTO(0, "bou", 3000, null, CategoryType.TAKE, StatusType.UNPAID);

        loanService.createLoan(loanDTO1);
        loanService.createLoan(loanDTO2);
        loanService.createLoan(loanDTO3);

        List<LoanDTO> loans = loanService.getLoans("bou");

        assertEquals(1, loans.size());
    }

}
