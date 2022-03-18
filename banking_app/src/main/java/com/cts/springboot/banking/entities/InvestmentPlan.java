package com.cts.springboot.banking.entities;

import lombok.*;

import javax.persistence.*;

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
    
    @Column
    private Long accountId;
    
    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    private Account account;

}
