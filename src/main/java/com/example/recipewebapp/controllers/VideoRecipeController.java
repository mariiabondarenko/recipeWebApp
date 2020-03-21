package com.example.recipewebapp.controllers;

import com.example.recipewebapp.entities.VideoRecipe;
import com.example.recipewebapp.services.VideoRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class VideoRecipeController {

    private VideoRecipeService videoRecipeService;


    public VideoRecipeController(VideoRecipeService videoRecipeService) {
        this.videoRecipeService = videoRecipeService;
    }


    @GetMapping("/video")
    public String getVideoRecipesForAdmin(Model model) {
        model.addAttribute("videoRecipes", videoRecipeService.getAllVideoRecipes());
        return "/admin/admin_video";
    }

    @GetMapping("/videorecipe")
    public String getVideoRecipes(Model model) {
        model.addAttribute("videoRecipes", videoRecipeService.getAllVideoRecipes());
        return "/video_recipe";
    }

    @GetMapping("/add_video_recipe")
    public String addVideoRecipe(Model model) {
        model.addAttribute("videoRecipe",new VideoRecipe());
        return "/admin/add_video_recipe";
    }

    @PostMapping("/add_video_recipe")
    public String addVideoRecipe(@Valid VideoRecipe videoRecipe, BindingResult result){
        if (result.hasErrors()) {
            return "add_video_recipe";
        } else {
            videoRecipeService.saveVideoRecipe(videoRecipe);
            return "redirect:/video";
        }
    }

    @RequestMapping(value = "/{id}/delvideo")
    public String delVideoRecipeById(@PathVariable(value = "id") long id){
        videoRecipeService.deleteVideoRecipeById(id);
        return "redirect:/video";
    }




}
