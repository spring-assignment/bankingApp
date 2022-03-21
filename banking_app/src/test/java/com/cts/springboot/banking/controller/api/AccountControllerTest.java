package com.cts.springboot.banking.controller.api;

import com.cts.springboot.banking.entities.Account;
import com.cts.springboot.banking.exception.AccountNotFoundException;
import com.cts.springboot.banking.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void listAllAccounts_whenGetMethod() throws Exception {

        Account account1 = new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));
        Account account2 = new Account(2L, "1873645027", "129809005", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(55000));

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

    @Test
    public void listAllAccount_whenGetMethod() throws Exception {

        List<Account> accountList = Arrays.asList(
                new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000)),
                new Account(2L, "1873645027", "129809005", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(55000))
        );

        when(accountService.findAll()).thenReturn(accountList);

        mvc.perform(
                MockMvcRequestBuilders.get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void getSpecificAccount_GetMethod() throws Exception {
        Account account = new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));

        given(accountService.findById(account.getAccountId()))
                .willReturn(account);

        mvc.perform(get("/api/accounts/" + account.getAccountId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("accountNumber", is(account.getAccountNumber())))
                .andExpect(jsonPath("panId", is(account.getPanId())))
                .andExpect((jsonPath("accountType", is(account.getAccountType()))))
                .andExpect(jsonPath("accountStatus", is(account.getAccountStatus())));
        //.andExpect(jsonPath("accountBalance", is(account.getAccountBalance())));

    }

    @Test
    public void save_account_success() throws Exception {

        Account account = new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));

        given(accountService.save(account))
                .willReturn(account);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/accounts")
                        .content(asJsonString(account))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void should_throw_exception_when_account_doesnt_exist() throws Exception {

        Account account = new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));

        Mockito.doThrow(new AccountNotFoundException("Account not found" + account.getAccountId())).when(accountService).save(account);
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/accounts")
                .content(mapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAccount_whenPutUser() throws Exception {

        Account account = new Account(1L, "1873645026", "129809004", LocalDate.of(1970, 8, 15), "Non-Account", "Active", BigDecimal.valueOf(45000));

        given(accountService.save(account))
                .willReturn(account);

        mvc.perform(
                MockMvcRequestBuilders.put("/api/accounts")
                        .content(asJsonString(account))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("panId", is(account.getPanId())));
    }


}

