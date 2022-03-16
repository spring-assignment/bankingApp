package com.cts.springboot.banking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long accountId;

    @Pattern(regexp = "\\d{10}")
    @Size(min = 10, max = 10, message = "It should consist of 10 digits")
    @Column
    private String accountNumber;

    @Column
    private String customerName;

    @Column
    private String panId;

    @Column
    private String phoneNumber;

    @NotNull
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @Column
    private String accountType;

    @Column
    private String accountStatus;

    @Column
    @NotNull
    @NotBlank(message = "Please enter your EmailId")
    private String emailId;

    @Column
    private BigDecimal accountBalance;

    public Account(long accountId, @Pattern(regexp = "\\d{10}") @Size(min = 10, max = 10, message = "It should consist of 10 digits") String accountNumber, BigDecimal accountBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account(long accountId, @Pattern(regexp = "\\d{10}") @Size(min = 10, max = 10, message = "It should consist of 10 digits") String accountNumber, String panId, LocalDate dateOfBirth, String accountType, String accountStatus, BigDecimal accountBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.panId = panId;
        this.dateOfBirth = dateOfBirth;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.accountBalance = accountBalance;
    }

    public void setAccountNumber(String accountNumber) {
        final String PATTERN = "\\d{10}";

        if (accountNumber.matches(PATTERN)) {
            this.accountNumber = accountNumber;
        } else {
            System.out.println("The value should consist of 10 digits");
        }

    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

        LocalDate date = LocalDate.now();
        long days = ChronoUnit.YEARS.between(dateOfBirth, date);

        if (days >= 18 && days <= 60) {
            this.dateOfBirth = dateOfBirth;
        } else {
            System.out.println("The person is not eligible to open the account");
        }
    }
}
