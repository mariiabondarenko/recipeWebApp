package com.example.recipewebapp.services;

import com.example.recipewebapp.entities.VideoRecipe;
import com.example.recipewebapp.repositories.VideoRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoRecipeService {

    private VideoRecipeRepository videoRecipeRepository;

    @Autowired
    public void setVideoRecipeRepository(VideoRecipeRepository videoRecipeRepository) {
        this.videoRecipeRepository = videoRecipeRepository;
    }

    public void saveVideoRecipe(VideoRecipe recipe){
        videoRecipeRepository.save(recipe);
    }

    public List<VideoRecipe> getAllVideoRecipes(){
        return videoRecipeRepository.findAll();
    }

    public Optional<VideoRecipe> getVideoRecipeById(Long id) {
        return videoRecipeRepository.findById(id);
    }

    public void deleteVideoRecipeById(Long id){
        videoRecipeRepository.deleteById(id);
    }
}
