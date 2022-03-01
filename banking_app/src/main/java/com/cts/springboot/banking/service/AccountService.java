package com.cts.springboot.banking.service;

import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.dto.AccountStatement;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.Transaction;

import java.util.List;

public interface AccountService {

    public List<Account> findAll();

    public Account findById(long theId);

    public Account save(Account account);

    public void deleteById(long theId);

    Transaction sendMoney(TransferBalanceRequest transferBalanceRequest);

    AccountStatement getStatement(String accountNumber);

}
