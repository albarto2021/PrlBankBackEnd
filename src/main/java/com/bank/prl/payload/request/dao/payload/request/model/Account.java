package com.bank.prl.payload.request.dao.payload.request.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String description;
    private BigDecimal accountBalance;
    private String accountType;
    private String accountStatusType;
    private Date createDate;
    private Date closedDate;
    private String Employee;


    @ManyToOne
    @JoinColumn(name = "user_id")
 //   @JsonIgnore
    private User user;

    @OneToMany(mappedBy="account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public Account(String description, BigDecimal accountBalance, String accountType,
                   String accountStatusType, Date createDate, Date closedDate, String employee) {
        this.description = description;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountStatusType = accountStatusType;
        this.createDate = createDate;
        this.closedDate = closedDate;
        Employee = employee;
    }

}
