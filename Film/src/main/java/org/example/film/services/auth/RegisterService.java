package org.example.film.services.auth;

import lombok.RequiredArgsConstructor;
import org.example.film.commons.cqrs.HandleResponse;
import org.example.film.commons.cqrs.IRequestHandler;
import org.example.film.models.dtos.RegisterDto;
import org.example.film.models.entities.Account;
import org.example.film.models.entities.Role;
import org.example.film.repositories.IAccountsRepository;
import org.example.film.repositories.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRequestHandler<RegisterDto, Account> {
    @Autowired
    private IAccountsRepository iAccountsRepository;
    @Autowired
    private IRolesRepository iRolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public HandleResponse<Account> handle(RegisterDto registerDto) throws Exception {
        var existAccount = iAccountsRepository.findByEmail(registerDto.getEmail());
        if(existAccount.isPresent()){
            return HandleResponse.error("Account has already exists.");
        }

        var roleDefault = iRolesRepository.findByName("ROLE_USER");
        if(roleDefault.isEmpty()){
            return HandleResponse.error("Role not found.");
        }

        var account = Account.builder()
                .userName(registerDto.getUserName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .email(registerDto.getEmail())
                .active(true)
                .avatar("/assets/public/images/avatar.png")
                .roles(roleDefault)
                .build();
        iAccountsRepository.save(account);
        return HandleResponse.ok(account);
    }
}
