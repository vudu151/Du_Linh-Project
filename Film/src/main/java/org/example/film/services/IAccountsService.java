package org.example.film.services;

import org.example.film.models.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAccountsService extends UserDetailsService, UserDetailsPasswordService {
}
