package com.leeonscoding.personal_apps.repositories;

import com.leeonscoding.personal_apps.entities.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
