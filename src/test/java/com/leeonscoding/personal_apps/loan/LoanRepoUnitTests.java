package com.leeonscoding.personal_apps.loan;

import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import com.leeonscoding.personal_apps.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class LoanRepoUnitTests {

    @Autowired
    private LoanRepository loanRepository;

    private Loan testLoan;

    @BeforeEach
    public void setUp() {
        int amount = 7000;
        String name = "kosai";

        testLoan = Loan
                .builder()
                .name(name)
                .amount(amount)
                .categoryType(CategoryType.TAKE)
                .statusType(StatusType.UNPAID)
                .createdAt(Instant.now())
                .build();

        loanRepository.save(testLoan);
    }

    @Test
    void testData() {
        Loan loan = loanRepository.findById(testLoan.getId()).orElse(null);

        assertNotNull(loan);
        assertEquals(loan.getName(), testLoan.getName());
        assertEquals(loan.getAmount(), testLoan.getAmount());
    }

    public void tearDown() {
        loanRepository.delete(testLoan);
    }
}
