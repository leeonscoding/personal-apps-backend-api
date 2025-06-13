package com.leeonscoding.personal_apps.dtos.loan;

import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.StatusType;

import java.util.Optional;

public record LoanSearchCriteria(
        int month,
        int year,
        Optional<String> name,
        Optional<CategoryType> category,
        Optional<StatusType> status
) {
}
