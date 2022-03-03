package com.cts.springboot.banking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String accountNumber;

    private String toAccountNumber;

    private BigDecimal transactionAmount;

    private LocalDate transactionDate;


    public Transaction(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
