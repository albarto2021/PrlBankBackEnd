package com.bank.prl.repository;

import com.bank.prl.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    // account, date, findById, findAll, user
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    /*List<Transaction> findByAccountIdAndDate(Long accountId, Date date);
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByDate(Date date);*/

}
