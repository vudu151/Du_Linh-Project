package org.example.film.services;

import org.example.film.commons.email.EmailService;
import org.example.film.configurations.securities.CustomUserDetails;
import org.example.film.models.entities.Account;
import org.example.film.models.entities.Role;
import org.example.film.repositories.IAccountsRepository;
import org.example.film.repositories.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountsService implements IAccountsService{
    @Autowired
    private IAccountsRepository iAccountsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var accountByEmail = iAccountsRepository.findByEmail(username);
        if(accountByEmail.isPresent()){
            return new CustomUserDetails(accountByEmail.get());
        }
        var accountById = iAccountsRepository.findById(UUID.fromString(username));
        if(accountById.isPresent()){
            return new CustomUserDetails(accountById.get());
        }
        throw new UsernameNotFoundException("Account not found");
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        var account = iAccountsRepository.findByEmail(user.getUsername());
        if(account.isEmpty()){
            throw new UsernameNotFoundException("Account not found.");
        }
        account.get().setPassword(passwordEncoder.encode(newPassword));
        iAccountsRepository.save(account.get());
        return new CustomUserDetails(account.get());
    }
}
