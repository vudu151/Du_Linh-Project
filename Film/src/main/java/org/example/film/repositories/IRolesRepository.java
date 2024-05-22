package org.example.film.repositories;

import org.example.film.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface IRolesRepository extends JpaRepository<Role, UUID> {
    Collection<Role> findByName(String name);
}
