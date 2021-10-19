package com.bank.prl.payload.request.dao.repository;

import com.bank.prl.payload.request.dao.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
