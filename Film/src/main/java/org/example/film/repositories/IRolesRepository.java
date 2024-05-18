package org.example.film.repositories;

import org.example.film.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, UUID> {
    public Roles findByName(String name);
}
