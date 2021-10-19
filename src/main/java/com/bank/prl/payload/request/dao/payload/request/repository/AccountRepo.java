package com.bank.prl.payload.request.dao.payload.request.repository;

import com.bank.prl.payload.request.dao.payload.request.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    Optional<Account> findById(Long id);
    Boolean existsByDescription(String description);
    Boolean existsByAccountType(String accountType);
}
