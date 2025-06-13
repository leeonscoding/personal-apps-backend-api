package com.leeonscoding.personal_apps.services.loan;

import com.leeonscoding.personal_apps.dtos.SortOrders;
import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.dtos.loan.LoanSearchCriteria;
import com.leeonscoding.personal_apps.dtos.loan.LoanSortFields;

import java.util.List;

public interface LoanService {
    LoanDTO createLoan(LoanDTO loanDTO);

    LoanDTO getLoanById(long loanId);

    List<LoanDTO> getLoans(LoanSearchCriteria searchCriteria, LoanSortFields sortFields, SortOrders orders);
}
