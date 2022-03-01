package com.cts.springboot.banking.controller.api;

import com.cts.springboot.banking.controller.request.AccountStatementRequest;
import com.cts.springboot.banking.controller.request.TransferBalanceRequest;
import com.cts.springboot.banking.controller.response.Response;
import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.exception.AccountNotFoundException;
import com.cts.springboot.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService theAccountService){
        accountService = theAccountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountService.findAll();
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "accountId") long theId){
        Account account = accountService.findById(theId);
        if(account == null){
            throw new AccountNotFoundException("The account id is not Found: "+theId);
        }
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account){
        account.setAccountId(0);
        accountService.save(account);
        return account;
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody Account account){
        accountService.save(account);
        return account;
    }

    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable(value = "accountId") long theId){
        Account account = accountService.findById(theId);
        if(account == null){
            throw new AccountNotFoundException("Account is not Found: "+theId);
        }
        accountService.deleteById(theId);
        return "Deleted AccountId is "+theId;
    }

    @RequestMapping("/sendMoney")
    public Response sendMoney(@RequestBody TransferBalanceRequest transferBalanceRequest){
        return Response.ok().setPayload(
                accountService.sendMoney(transferBalanceRequest)
        );
    }

    @RequestMapping("/statement/{accountNumber}")
    public Response getStatement(@PathVariable String accountNumber,@RequestBody AccountStatementRequest accountStatementRequest){
        return Response.ok().setPayload(
                accountService.getStatement(accountStatementRequest.getAccountNumber())
        );
    }


}
