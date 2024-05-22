package org.example.film.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private Boolean gender;

    @Email
    @Column(name = "email", unique = true)
    private String email;

//    @Column(name = "activation_code")
//    private String activationCode; // Ma kich hoat khi dang ki tai khoan thanh cong

    @Column(name = "remember")
    private Boolean remember;

    @Column(name = "avatar")
    private String avatar = "/assets/public/images/avatar.png";

    @Column(name = "google_id")
    private String google_id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "level")
    private int level;

    //Many-To-Many: Accounts-Roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}
