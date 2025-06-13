package com.leeonscoding.personal_apps.services.loan;

import com.leeonscoding.personal_apps.dtos.SortOrders;
import com.leeonscoding.personal_apps.dtos.loan.LoanDTO;
import com.leeonscoding.personal_apps.dtos.loan.LoanSearchCriteria;
import com.leeonscoding.personal_apps.dtos.loan.LoanSortFields;
import com.leeonscoding.personal_apps.entities.loan.Loan;
import com.leeonscoding.personal_apps.repositories.LoanRepository;
import com.leeonscoding.personal_apps.repositories.LoanSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        return entityToDto(loanRepository.save(dtoToEntity(loanDTO)));
    }

    @Override
    public LoanDTO getLoanById(long loanId) {
        return entityToDto(loanRepository.findById(loanId).orElseThrow());
    }

    @Override
    public List<LoanDTO> getLoans(LoanSearchCriteria searchCriteria, LoanSortFields sortFields, SortOrders orders) {

        Specification<Loan> loanSpecification = LoanSpecification
                .byMonthAndYear(searchCriteria.month(), searchCriteria.year());

        if (searchCriteria.name().isPresent()) {
            loanSpecification = loanSpecification.and(LoanSpecification.byName(searchCriteria.name().get()));
        }
        if (searchCriteria.category().isPresent()) {
            loanSpecification = loanSpecification.and(LoanSpecification.byCategory(searchCriteria.category().get()));
        }
        if (searchCriteria.status().isPresent()) {
            loanSpecification = loanSpecification.and(LoanSpecification.byStatus(searchCriteria.status().get()));
        }

        Sort.TypedSort<Loan> loanSort = Sort.sort(Loan.class);
        Sort sort;

        if (sortFields == LoanSortFields.MONTH) {
            sort = loanSort.by(Loan::getCreatedAt);
        } else {
            sort = loanSort.by(Loan::getName);
        }

        if (orders == SortOrders.ASC) {
            sort = loanSort.ascending();
        } else {
            sort = loanSort.descending();
        }

        return loanRepository
                .findAll(loanSpecification, sort)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO updateLoan(long loanId, LoanDTO loanDTO) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);

        if(loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setName(loanDTO.name());
            loan.setAmount(loanDTO.amount());
            loan.setCategoryType(loanDTO.categoryType());
            loan.setStatusType(loanDTO.statusType());

            return entityToDto(loanRepository.save(loan));
        }
        throw new RuntimeException("can't update");
    }

    @Override
    public void deleteLoan(long loanId) {
        loanRepository.deleteById(loanId);
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
