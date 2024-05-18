package org.example.film.services;

import org.example.film.entities.Accounts;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountsService extends UserDetailsService {
    Accounts findByUserName(String userName);
    void save(Accounts accounts);
}
