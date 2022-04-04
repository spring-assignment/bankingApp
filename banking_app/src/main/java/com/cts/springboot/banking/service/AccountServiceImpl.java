package com.cts.springboot.banking.service;

import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.dto.AccountStatement;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.Transaction;
import com.cts.springboot.banking.exception.TransactionNotFoundException;
import com.cts.springboot.banking.repository.AccountRepository;
import com.cts.springboot.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public AccountServiceImpl() {

    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long theId) {
        Optional<Account> account = accountRepository.findById(theId);
        Account result = null;
        if (account.isPresent()) {
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

    public Account findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account;
    }

    @Override
    @Async
    public Transaction sendMoney(TransferBalanceRequest transferBalanceRequest) {

        String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
        String toAccountNumber = transferBalanceRequest.getToAccountNumber();
        BigDecimal amount = transferBalanceRequest.getAmount();


        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        if ((fromAccount.getAccountBalance().compareTo(BigDecimal.ONE) > 0) &&
                (fromAccount.getAccountBalance().compareTo(amount) > 0)) {

            fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(amount));
            accountRepository.save(fromAccount);
            toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));
            accountRepository.save(toAccount);
            Transaction transaction = transactionRepository.save(new Transaction(0L, fromAccount.getAccountNumber(), toAccount.getAccountNumber(), amount, LocalDate.now()));
            return transaction;

        } else if ((amount.compareTo(fromAccount.getAccountBalance()) > 0) || (amount.compareTo(BigDecimal.ZERO) < 0)) {             // withdraw value exceeds balance
            transferBalanceRequest.getFromAccountNumber();
            throw new TransactionNotFoundException("Balance is insufficient, your current balance: " + fromAccount.getAccountBalance() + ", but the amount that need to be transferred: " + amount);

        }
        return null;
    }

    @Override
    public AccountStatement getStatement(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        return new AccountStatement(account.getAccountBalance(), transactionRepository.findByAccountNumber(accountNumber));
    }

    @Override
    public AccountStatement getStatementByDate(String accountNumber, String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fromLocalDate = LocalDate.parse(fromDate, formatter);
        LocalDate toLocalDate = LocalDate.parse(toDate, formatter);
        Account account = accountRepository.findByAccountNumber(accountNumber);

        return new AccountStatement(account.getAccountBalance(), transactionRepository.findByAccountNumberByDate(accountNumber, fromLocalDate, toLocalDate));
    }

}
