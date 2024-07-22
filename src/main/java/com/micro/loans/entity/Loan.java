package com.micro.loans.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Loan {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long loanId;

    @Column
    private String mobileNumber;

    @Column
    private String loanNumber;

    @Column
    private String loanType;

    @Column
    private long totalLoan;

    @Column
    private long amountPaid;

    @Column
    private long outstandingAmount;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;
}
