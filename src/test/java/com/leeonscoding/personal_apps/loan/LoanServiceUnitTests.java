package com.leeonscoding.personal_apps.loan;

import com.leeonscoding.personal_apps.dtos.SortOrders;
import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.dtos.loan.LoanSearchCriteria;
import com.leeonscoding.personal_apps.dtos.loan.LoanSortFields;
import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import com.leeonscoding.personal_apps.services.loan.LoanServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanServiceUnitTests {

    @Autowired
    private LoanServiceImpl loanService;

    LoanDTO newLoan = null;

    @BeforeAll
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

        LocalDate nowDate = LocalDate.now();
        int month = nowDate.getMonth().getValue();
        int year = nowDate.getYear();

        LoanSearchCriteria searchCriteria = new LoanSearchCriteria(month, year, Optional.of("mama"), Optional.empty(), Optional.empty());

        List<LoanDTO> loans = loanService.getLoans(searchCriteria, LoanSortFields.NAME, SortOrders.DESC);

        assertEquals(1, loans.size());
    }

    @Test
    void editTest() {
        LoanDTO updateDto = new LoanDTO(newLoan.id(), "kosai", 7000, null, CategoryType.GIVE, StatusType.UNPAID);
        loanService.updateLoan(newLoan.id(), updateDto);

        LoanDTO loan = loanService.getLoanById(newLoan.id());

        assertEquals(newLoan.amount(), loan.amount());
    }

    @Test
    void deleteTest() {

        String name = newLoan.name();

        loanService.deleteLoan(newLoan.id());

        LocalDate nowDate = LocalDate.now();
        int month = nowDate.getMonth().getValue();
        int year = nowDate.getYear();

        LoanSearchCriteria searchCriteria = new LoanSearchCriteria(month, year, Optional.of(name), Optional.empty(), Optional.empty());

        List<LoanDTO> loans = loanService.getLoans(searchCriteria, LoanSortFields.NAME, SortOrders.DESC);

        assertEquals(0, loans.size());
    }

}
