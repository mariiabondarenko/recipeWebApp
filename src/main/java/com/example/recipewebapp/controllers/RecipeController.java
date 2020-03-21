package com.example.recipewebapp.controllers;

import com.example.recipewebapp.entities.Category;
import com.example.recipewebapp.entities.Recipe;
import com.example.recipewebapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RecipeController {

    private RecipeService recipeService;
    private Category category;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }

    @GetMapping("/add_recipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe",new Recipe());
        return "add_recipe";
    }

    @RequestMapping(value = "/imagedisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Long itemId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        Recipe item = recipeService.getRecipeById(itemId).get();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(item.getImage());

        response.getOutputStream().close();
    }


    @PostMapping("/add_recipe")
    public String greetingSubmit(@Valid Recipe recipe, BindingResult result){
        if (result.hasErrors()) {
            return "add_recipe";
        } else {
            System.out.println("");
            recipeService.saveRecipe(recipe);
            return "redirect:/";
        }
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/{id}/one_recipe")
    public String showById(@PathVariable String id, Model model){
        recipeService.getRecipeById(new Long(id)).ifPresent(o -> model.addAttribute("recipe", o));
        return "one_recipe";
    }

    @GetMapping("/{category}")
    public String getRecipeByPathVeriableCategory(@PathVariable(value = "category")Category category, Model model) {
        model.addAttribute("recipes",  recipeService.getRecipeByCategory(category));
        return "index";
    }

    @RequestMapping(value = "/search/")
    public String findRecipeByName(@RequestParam("search")String name, Model model) {
        model.addAttribute("recipes", this.recipeService.getRecipeByName(name));
        return "index";
    }


    @RequestMapping(value = "/sort/")
    public String orderRecipeBy(@RequestParam(value = "type")String type, Model model) {
        switch(type) {
            case "nameasc": model.addAttribute("recipes", this.recipeService.getAllByOrderByNameAsc()); break;
            case "namedesc": model.addAttribute("recipes", this.recipeService.getAllByOrderByNameDesc()); break;
            case "servingsasc": model.addAttribute("recipes", this.recipeService.getAllByOrderByServingsAsc()); break;
            case "servingsdesc": model.addAttribute("recipes", this.recipeService.getAllByOrderByServingsDesc()); break;

        }
        return "index";
    }

    @RequestMapping(value = "/{id}/del")
    public String delRecipeById(@PathVariable(value = "id") long id){
        recipeService.deleteById(id);
        return "redirect:/admin/";
    }





}
