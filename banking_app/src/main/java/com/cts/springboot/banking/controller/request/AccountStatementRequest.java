package com.cts.springboot.banking.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class AccountStatementRequest {

    private String accountNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String fromDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String toDate;

    public void setFromDate(String fromDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = fromDate;
        LocalDate localDate = LocalDate.parse(date, formatter);         //convert String to LocalDate
        this.fromDate = String.valueOf(localDate);

    }

    public void setToDate(String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = toDate;
        LocalDate localDate = LocalDate.parse(date, formatter);
        this.toDate = String.valueOf(localDate);                        //convert String to LocalDate

    }
}
