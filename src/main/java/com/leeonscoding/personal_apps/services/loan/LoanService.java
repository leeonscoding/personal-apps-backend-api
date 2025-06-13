package com.leeonscoding.personal_apps.services.loan;

import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;

import java.util.List;

public interface LoanService {
    LoanDTO createLoan(LoanDTO loanDTO);

    LoanDTO getLoanById(long loanId);

    List<LoanDTO> getLoans(String name);
}
