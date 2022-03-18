package com.cts.springboot.banking.service;

import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.dto.AccountStatement;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.InvestmentPlan;
import com.cts.springboot.banking.entities.Transaction;

import java.util.List;

public interface InvestmentPlanService {

    InvestmentPlan findById(long theId);

    //InvestmentPlan findByAccountId(Long accountId);
}
