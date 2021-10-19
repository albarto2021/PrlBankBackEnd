package com.bank.prl.payload.request.dao.payload.request.repository;

import com.bank.prl.payload.request.dao.payload.request.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
