package com.bank.prl.payload.request.dao.repository;

import com.bank.prl.payload.request.dao.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    Optional<Account> findById(Long id);
    Boolean existsByDescription(String description);
    Boolean existsByAccountType(String accountType);
}
