package com.leeonscoding.personal_apps.repositories;

import com.leeonscoding.personal_apps.entities.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
