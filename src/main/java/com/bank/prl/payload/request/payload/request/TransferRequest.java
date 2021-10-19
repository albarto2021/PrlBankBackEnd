package com.bank.prl.payload.request.payload.request;

import lombok.Data;

@Data
public class TransferRequest {

    private Long fromAccount;

    private Long toAccount;

    private Double amount;

    private String explanation;

}
