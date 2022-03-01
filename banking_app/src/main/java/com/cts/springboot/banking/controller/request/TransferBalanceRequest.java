package com.cts.springboot.banking.controller.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferBalanceRequest {

    private String fromAccountNumber;

    private String toAccountNumber;

    private BigDecimal amount;

}
