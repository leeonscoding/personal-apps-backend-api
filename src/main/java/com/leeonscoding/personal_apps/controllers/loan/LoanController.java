package com.leeonscoding.personal_apps.controllers.loan;

import com.leeonscoding.personal_apps.dtos.SortOrders;
import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.dtos.loan.LoanSearchCriteria;
import com.leeonscoding.personal_apps.dtos.loan.LoanSortFields;
import com.leeonscoding.personal_apps.services.loan.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoanController {

    public final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanDTO> createLoans(@RequestBody LoanDTO loanDTO) {
        LoanDTO loan = loanService.createLoan(loanDTO);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getLoans(
            LoanSearchCriteria searchCriteria,
            LoanSortFields sort,
            SortOrders order) {

        List<LoanDTO> dtoList = loanService.getLoans(searchCriteria, sort, order);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable("id") long loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable("id") long loanId, @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.updateLoan(loanId, loanDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") long loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
