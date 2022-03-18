package com.cts.springboot.banking.repository;

import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.InvestmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentPlanRepository extends JpaRepository<InvestmentPlan, Long> {

    //InvestmentPlan findById(long theId);

    InvestmentPlan findByAccountId(long accountId);

}
