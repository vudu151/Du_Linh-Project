package org.example.film.repositories;

import org.example.film.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts, UUID> {
    public Accounts findByUserName(String userName);
}
