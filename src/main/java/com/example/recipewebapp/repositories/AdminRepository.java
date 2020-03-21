package com.example.recipewebapp.repositories;

import com.example.recipewebapp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
