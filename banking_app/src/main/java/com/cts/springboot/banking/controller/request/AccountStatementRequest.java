package com.cts.springboot.banking.controller.request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class AccountStatementRequest {
    private String accountNumber;
}
