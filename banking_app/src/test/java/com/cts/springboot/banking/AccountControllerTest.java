package com.cts.springboot.banking.controller.api;

import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void listAllUsers_whenGetMethod() throws Exception {

        Account account1 = new Account(1L, "1873645026", 129809004, new Date(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));
        Account account2 = new Account(2L, "1873645027", 129809005, new Date(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(55000));

        List<Account> allAccounts = Arrays.asList(account1, account2);

        given(accountService
                .findAll())
                .willReturn(allAccounts);

        mvc.perform(get("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].accountNumber", is(account1.getAccountNumber())))
                .andExpect(jsonPath("$[0].panId", is(account1.getPanId())))
                .andExpect(jsonPath("$[0].accountType", is(account1.getAccountType())))
                .andExpect(jsonPath("$[1].accountNumber", is(account2.getAccountNumber())))
                .andExpect(jsonPath("$[1].panId", is(account2.getPanId())))
                .andExpect(jsonPath("$[1].accountType", is(account2.getAccountType())));
    }
}





