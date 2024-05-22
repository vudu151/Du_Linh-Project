package org.example.film.configurations.securities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.film.models.entities.Account;
import org.example.film.models.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequiredArgsConstructor
@Transactional
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Account account;

    //Convert 1 collection 'roles' : Collection<Roles> roles = Arrays.asList(new Roles("ROLE_ADMIN"), new Roles("ROLE_USER"));
    //Thanh: [ROLE_ADMIN, ROLE_USER]
    private Collection<? extends GrantedAuthority> rolesToAuthories(Collection<Role> roles) {
        return roles.stream().map(
                        role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesToAuthories(account.getRoles());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
