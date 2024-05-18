package org.example.film.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.film.entities.Accounts;
import org.example.film.entities.Roles;
import org.example.film.repositories.IAccountsRepository;
import org.example.film.repositories.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountsService implements IAccountsService{
    private IAccountsRepository iAccountsRepository;
    private IRolesRepository iRolesRepository;
    @Autowired
    public AccountsService(IAccountsRepository iAccountsRepository, IRolesRepository iRolesRepository) {
        this.iAccountsRepository = iAccountsRepository;
        this.iRolesRepository = iRolesRepository;
    }

//  //Create Data virtual account
//    @PostConstruct
//    public void insertAccount(){
//        Roles role1 = new Roles();
//        role1.setName("ROLE_ADMIN");
//        Collection<Roles> roles = new ArrayList<>();
//        roles.add(role1);
//
//        Accounts accounts1 = new Accounts();
//        accounts1.setUserName("admin");
//        accounts1.setPassword("$2a$12$5BL5PJxD6icmoPhSv5ATeOcwm62y0W48tsSnZdOgm7N0vGeNftPU.");
//        accounts1.setFullName("Vu Xuan Du");
//        accounts1.setAddress("Hai Phong");
//        accounts1.setGender(true);
//        accounts1.setEmail("vudu151@gmail.com");
//        accounts1.setRemember(true);
//        accounts1.setAvatar("/assets/public/images/avatar.png");
//        accounts1.setLevel(1);
//        accounts1.setRoles(roles);
//
//        iAccountsRepository.save(accounts1);
//    }

    @Override
    public Accounts findByUserName(String userName) {
        return iAccountsRepository.findByUserName(userName);
    }

    @Override
    public void save(Accounts accounts) {
        iAccountsRepository.save(accounts);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = iAccountsRepository.findByUserName(username);
        if (accounts == null){
            throw new UsernameNotFoundException("Invalid Username or Password.");
        }
        return new org.springframework.security.core.userdetails.User(accounts.getUserName(), accounts.getPassword(), rolesToAuthories(accounts.getRoles()));
    }

    //Convert 1 collection 'roles' : Collection<Roles> roles = Arrays.asList(new Roles("ROLE_ADMIN"), new Roles("ROLE_USER"));
    //Thanh: [ROLE_ADMIN, ROLE_USER]
    private Collection<? extends GrantedAuthority> rolesToAuthories(Collection<Roles> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
