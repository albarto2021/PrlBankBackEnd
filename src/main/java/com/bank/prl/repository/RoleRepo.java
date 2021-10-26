package com.bank.prl.repository;

import com.bank.prl.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
    List<Role> findAll();

}
