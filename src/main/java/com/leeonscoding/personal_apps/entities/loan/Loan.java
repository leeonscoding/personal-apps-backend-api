package com.leeonscoding.personal_apps.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @CreatedDate
    private Instant createdAt;

    private LocalDate dueDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusType statusType;
}
