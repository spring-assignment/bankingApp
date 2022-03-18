package com.cts.springboot.banking.controller.api;

import com.cts.springboot.banking.controller.request.AccountStatementRequest;
import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.controller.response.Response;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.entities.InvestmentPlan;
import com.cts.springboot.banking.exception.AccountNotFoundException;
import com.cts.springboot.banking.service.AccountService;
import com.cts.springboot.banking.service.InvestmentPlanService;
import com.cts.springboot.banking.service.InvestmentPlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@RequestMapping("/api")
public class InvestmentPlanController {

    @Autowired
    private InvestmentPlanServiceImpl investmentPlanService;

    @Autowired
    public InvestmentPlanController(InvestmentPlanServiceImpl theInvestmentPlanService) {
        investmentPlanService = theInvestmentPlanService;
    }


    @GetMapping("/plan/{planId}")
    public ResponseEntity<InvestmentPlan> getById(@PathVariable(name = "planId") long theId) {
        InvestmentPlan inv = investmentPlanService.findById(theId);
        if (inv == null) {
            throw new AccountNotFoundException("The account id is not Found: " + theId);
        }
        return new ResponseEntity<InvestmentPlan>(inv, HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public InvestmentPlan getAccountById(@PathVariable(name = "accountId") long theId,@RequestParam long accId) {
        InvestmentPlan inv = investmentPlanService.findByAccountId(accId);
        return inv;
        //return investmentPlanService.findById(1L);
    }
}
