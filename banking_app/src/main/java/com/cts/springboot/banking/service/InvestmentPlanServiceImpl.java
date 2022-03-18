package com.cts.springboot.banking.service;


import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.InvestmentPlan;
import com.cts.springboot.banking.repository.AccountRepository;
import com.cts.springboot.banking.repository.InvestmentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class InvestmentPlanServiceImpl implements InvestmentPlanService {

    private InvestmentPlanRepository investmentPlanRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    public InvestmentPlanServiceImpl(InvestmentPlanRepository theInvestmentPlanRepository) {
        investmentPlanRepository = theInvestmentPlanRepository;
    }


    public InvestmentPlanServiceImpl() {
    }

    @Override
    public InvestmentPlan findById(long theId) {
        Optional<InvestmentPlan> investmentPlan = investmentPlanRepository.findById(theId);
        InvestmentPlan result = null;
        if (investmentPlan.isPresent()) {
            result = investmentPlan.get();
        }
        return result;
    }

    public InvestmentPlan findByAccountId(Long accountId) {
            Account account = accountService.findById(accountId);
            InvestmentPlan plan = null;
            BigDecimal balanceAmount = account.getAccountBalance();
            BigDecimal reqMaxAmount = new BigDecimal(200000);
            BigDecimal reqMinAmount = new BigDecimal(100000);
            //if (account.isPresent()) {
                if (balanceAmount.compareTo(reqMaxAmount) == 1) {
                    return investmentPlanRepository.getById(2L);
                } else if (balanceAmount.compareTo(reqMinAmount) == 1) {
                    return investmentPlanRepository.getById(1L);
                }
            //}
            return plan;
        }

    }








