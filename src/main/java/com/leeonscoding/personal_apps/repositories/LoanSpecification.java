package com.leeonscoding.personal_apps.repositories;

import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.entities.loan.Loan_;
import org.springframework.data.jpa.domain.Specification;

public class LoanSpecification  {

    public static Specification<Loan> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Loan_.NAME), name);
    }

}
