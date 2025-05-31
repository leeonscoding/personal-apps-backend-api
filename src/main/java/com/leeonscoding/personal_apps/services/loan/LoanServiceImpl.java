package com.leeonscoding.personal_apps.services.loan;

import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.repositories.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public Loan createLoan(LoanDTO loanDTO) {
        return loanRepository.save(dtoToEntity(loanDTO));
    }

    @Override
    public LoanDTO getLoanById(long loanId) {
        return entityToDto(loanRepository.findById(loanId).orElseThrow());
    }

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository
                .findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private LoanDTO entityToDto(Loan loan) {
        return new LoanDTO(loan.getId(),
                loan.getName(),
                loan.getAmount(),
                loan.getDueDate(),
                loan.getCategoryType(),
                loan.getStatusType());
    }

    private Loan dtoToEntity(LoanDTO loanDTO) {
        return Loan.builder()
                .name(loanDTO.name())
                .amount(loanDTO.amount())
                .createdAt(Instant.now())
                .categoryType(loanDTO.categoryType())
                .statusType(loanDTO.statusType())
                .dueDate(loanDTO.dueDate())
                .build();
    }

}
