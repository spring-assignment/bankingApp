package com.cts.springboot.banking.service;

import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.dto.AccountStatement;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.Transaction;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long theId);

    Account save(Account account);

    void deleteById(long theId);

    Transaction sendMoney(TransferBalanceRequest transferBalanceRequest);

    AccountStatement getStatement(String accountNumber);

    AccountStatement getStatementByDate(String accountNumber, String fromDate, String toDate);

}

