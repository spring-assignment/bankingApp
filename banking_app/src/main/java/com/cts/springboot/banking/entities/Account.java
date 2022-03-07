package com.cts.springboot.banking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long accountId;

    @Pattern(regexp="\\d{10}")
    @Size(min=10,max=10, message = "It should consist of 10 digits")
    @Column
    private String accountNumber;

    @Column
    private int panId;

    @NotNull
    @Column
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;

    @Column
    private String accountType;

    @Column
    private String accountStatus;

    @Column
    private BigDecimal accountBalance;

    public Account(long accountId, @Pattern(regexp = "\\d{10}") @Size(min = 10, max = 10, message = "It should consist of 10 digits") String accountNumber, BigDecimal accountBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public void setAccountNumber(String accountNumber){
       final String PATTERN = "\\d{10}";

       if(accountNumber.matches(PATTERN)){
           this.accountNumber = accountNumber;
       } else {
           System.out.println("The value should consist of 10 digits");
       }

    }

    public void setDateOfBirth(Date dateOfBirth) {
        LocalDate dateValue = dateOfBirth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate date = LocalDate.now();
        long days = ChronoUnit.YEARS.between(dateValue, date);

        if(days>=18 && days<=60){
            this.dateOfBirth = dateOfBirth;
        } else{
            System.out.println("The person is not eligible to open the account");
        }
    }
}
