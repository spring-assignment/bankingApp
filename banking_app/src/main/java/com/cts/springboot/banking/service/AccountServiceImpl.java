package com.cts.springboot.banking.service;

import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.dto.AccountStatement;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.Transaction;
import com.cts.springboot.banking.repository.AccountRepository;
import com.cts.springboot.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository theAccountRepository) {
        accountRepository = theAccountRepository;
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long theId) {
        Optional<Account> account = accountRepository.findById(theId);
        Account result = null;
        if(account.isPresent()){
            result = account.get();
        }
        return result;
    }

    @Override
    public Account save(Account account) {
        accountRepository.save(account);
        return accountRepository.findByAccountNumber(account.getAccountNumber());
    }

    @Override
    public void deleteById(long theId) {
        accountRepository.deleteById(theId);
    }

    public Account findByAccountNumber(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account;
    }

    @Override
    public Transaction sendMoney(TransferBalanceRequest transferBalanceRequest) {

        String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        BigDecimal amount = transferBalanceRequest.getAmount();

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        if((fromAccount.getAccountBalance().compareTo(BigDecimal.ONE) == 1) &&
                (fromAccount.getAccountBalance().compareTo(amount) == 1)) {
            fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(amount));
            accountRepository.save(fromAccount);
            toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));
            accountRepository.save(toAccount);
            Transaction transaction = transactionRepository.save(new Transaction(0L, fromAccount.getAccountNumber(), amount, new Timestamp(System.currentTimeMillis())));
            return transaction;

        }
        return null;
    }

    @Override
    public AccountStatement getStatement(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        return new AccountStatement(account.getAccountBalance(),transactionRepository.findByAccountNumber(accountNumber));
    }



}
