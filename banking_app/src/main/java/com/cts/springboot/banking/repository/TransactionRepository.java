package com.cts.springboot.banking.repository;

import com.cts.springboot.banking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(String accountNumber);

    @Query("select a from Transaction a where a.accountNumber=:accountNumber AND a.transactionDate BETWEEN :fromDate AND :toDate")
    List<Transaction> findByAccountNumberByDate(@Param("accountNumber") String accountNumber, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
