package com.project.bookstore.bookstore.repository;


import com.project.bookstore.bookstore.model.ERole;
import com.project.bookstore.bookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
