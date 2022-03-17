package com.cts.springboot.banking.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "investmentPlan")
public class InvestmentPlan {

    @Id
    @Column
    private long investmentId;

    @Column
    private String planName;

    @Column
    private String planDescription;

}
