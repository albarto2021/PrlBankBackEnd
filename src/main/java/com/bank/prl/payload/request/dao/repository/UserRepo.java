package com.bank.prl.payload.request.dao.repository;

import com.bank.prl.payload.request.dao.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findBySsn(String ssn);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsBySsn(String ssn);
//  id, ssn, first, last, dob, email, username, pass
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName=?2, u.lastName=?3, u.email=?4 WHERE u.ssn=?1")
    void update(String ssn, String firstName,
                String lastName, String email);

}
