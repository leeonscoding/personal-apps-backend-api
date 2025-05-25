package com.leeonscoding.personal_apps.dtos.loan;

import com.leeonscoding.personal_apps.entities.loan.CategoryType;
import com.leeonscoding.personal_apps.entities.loan.StatusType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LoanDTO(
        long id,

        @NotNull(message = "name can't be null")
        @Size(min = 1, max = 100, message = "length should be between 1 to 100")
        String name,

        @NotNull(message = "amount can't be null")
        int amount,

        LocalDate dueDate,

        @NotNull
        CategoryType categoryType,

        @NotNull
        StatusType statusType
) {}
