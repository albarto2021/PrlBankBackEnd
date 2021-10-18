package com.bank.prl.payload.request;

import com.bank.prl.model.Account;
import lombok.Data;

@Data
public class TransferRequest {

    private Account fromAccount;

    private Account toAccount;

    private Double amount;

    private String explanation;

}
