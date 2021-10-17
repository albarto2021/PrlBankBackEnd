package com.bank.prl.repository;

import com.bank.prl.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
