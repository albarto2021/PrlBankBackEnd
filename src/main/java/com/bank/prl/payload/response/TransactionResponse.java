package com.bank.prl.payload.response;

import java.util.List;

import com.bank.prl.dao.TransactionDAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponse {
	List<TransactionDAO> allTransactionDAOList;
}
