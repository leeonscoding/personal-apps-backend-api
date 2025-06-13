package com.leeonscoding.personal_apps.repositories;

import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.entities.loan.Loan_;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class LoanSpecification {

    public static Specification<Loan> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Loan_.NAME), name);
    }

    public static Specification<Loan> byMonthAndYear(int month, int year) {

        return (root, query, criteriaBuilder) -> {
            Expression<Integer> monthExpr = criteriaBuilder.function("month", Integer.class, root.get(Loan_.CREATED_AT));
            Expression<Integer> yearExpr = criteriaBuilder.function("year", Integer.class, root.get(Loan_.CREATED_AT));

            return criteriaBuilder.and(
                    criteriaBuilder.equal(monthExpr, month),
                    criteriaBuilder.equal(yearExpr, year)
            );
        };

    }

    public static Specification<Loan> byCategory(CategoryType categoryType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Loan_.CATEGORY_TYPE), categoryType);
    }

    public static Specification<Loan> byStatus(StatusType statusType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Loan_.STATUS_TYPE), statusType);
    }

}
