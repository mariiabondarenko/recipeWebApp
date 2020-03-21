package com.example.recipewebapp.services;

import com.example.recipewebapp.entities.Category;
import com.example.recipewebapp.entities.Recipe;
import com.example.recipewebapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService{

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRepository(RecipeRepository repository){
        this.recipeRepository = repository;
    }

    public void saveRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipeByCategory(Category category) {
        return getAllRecipes().stream()
                .filter(recipe1 -> recipe1.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipeByName(String name) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Recipe> getAllByOrderByNameAsc(){
        return recipeRepository.findAllByOrderByNameAsc();
    }
    public List<Recipe> getAllByOrderByNameDesc(){
        return recipeRepository.findAllByOrderByNameDesc();
    }
    public List<Recipe> getAllByOrderByServingsAsc(){
        return recipeRepository.findAllByOrderByServingsAsc();
    }
    public List<Recipe> getAllByOrderByServingsDesc(){
        return recipeRepository.findAllByOrderByServingsDesc();
    }
    public void deleteById(Long id){
        recipeRepository.deleteById(id);
    }

}
