package com.cts.springboot.banking.dto;

import com.cts.springboot.banking.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatement {
    private BigDecimal currentBalance;
    private List<Transaction> transactionHistory;

}
