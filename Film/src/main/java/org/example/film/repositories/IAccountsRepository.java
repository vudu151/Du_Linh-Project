package org.example.film.repositories;

import org.example.film.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAccountsRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUserName(String userName);
    Optional<Account> findByEmail(String email);
}
