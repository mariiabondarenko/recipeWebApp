package com.example.recipewebapp.repositories;

import com.example.recipewebapp.entities.VideoRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRecipeRepository extends JpaRepository<VideoRecipe, Long> {
    void deleteById(Long id);
}
