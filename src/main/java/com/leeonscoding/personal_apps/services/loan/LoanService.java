package com.leeonscoding.personal_apps.services.loan;

import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.entities.loan.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanDTO loanDTO);

    LoanDTO getLoanById(long loanId);

    List<LoanDTO> getLoans();
}
