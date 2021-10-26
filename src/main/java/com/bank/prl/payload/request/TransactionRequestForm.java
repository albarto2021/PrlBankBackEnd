package com.bank.prl.payload.request;

import com.bank.prl.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TransactionRequestForm {

    private double amount;
    private String comment;
    private Long accountDescriptions;


}
