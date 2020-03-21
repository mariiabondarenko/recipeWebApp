package com.example.recipewebapp.repositories;

import com.example.recipewebapp.entities.Category;
import com.example.recipewebapp.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> getRecipeByCategory(Category category);
    List<Recipe> findAllByOrderByNameAsc();
    List<Recipe> findAllByOrderByNameDesc();
    List<Recipe> findAllByOrderByServingsAsc();
    List<Recipe> findAllByOrderByServingsDesc();
    void deleteById(Long id);
}
