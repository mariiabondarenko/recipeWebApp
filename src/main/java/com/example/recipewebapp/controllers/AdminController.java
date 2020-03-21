package com.example.recipewebapp.controllers;

import com.example.recipewebapp.entities.Category;
import com.example.recipewebapp.entities.Recipe;
import com.example.recipewebapp.services.AdminService;
import com.example.recipewebapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {

    private AdminService adminService;
    private RecipeService recipeService;


    public AdminController(RecipeService recipeService,AdminService adminService) {
        this.adminService = adminService;
        this.recipeService = recipeService;
    }

    @GetMapping("/admin/")
    public String index(Model model) {
        model.addAttribute("recipes", this.recipeService.getAllRecipes());
        return "/admin/admin_index";
    }


    @GetMapping("/admin/add_recipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe",new Recipe());
        return "/admin/admin_add_recipe";
    }


    @PostMapping("/admin/add_recipe")
    public String greetingSubmit(@Valid Recipe recipe, BindingResult result){
        if (result.hasErrors()) {
            return "/admin/admin_add_recipe";
        } else {
            recipeService.saveRecipe(recipe);
            return "redirect:/admin/";
        }
    }

    @GetMapping("/admin/{id}/one_recipe")
    public String showById(@PathVariable String id, Model model){
        this.recipeService.getRecipeById(new Long(id)).ifPresent(o -> model.addAttribute("recipe", o));
        return "/admin/admin_one_recipe";
    }


    @GetMapping("/admin/{category}")
    public String getRecipeByPathVeriableCategory(@PathVariable(value = "category")Category category, Model model) {
        model.addAttribute("recipes",  recipeService.getRecipeByCategory(category));
        return "/admin/admin_index";
    }

    @RequestMapping(value = "/admin/search/")
    public String findRecipeByName(@RequestParam("search")String name, Model model) {
        model.addAttribute("recipes", this.recipeService.getRecipeByName(name));
        return "/admin/admin_index";
    }

    @RequestMapping(value = "/admin/sort/")
    public String orderRecipeBy(@RequestParam(value = "type")String type, Model model) {
        switch(type) {
            case "nameasc": model.addAttribute("recipes", this.recipeService.getAllByOrderByNameAsc()); break;
            case "namedesc": model.addAttribute("recipes", this.recipeService.getAllByOrderByNameDesc()); break;
            case "servingsasc": model.addAttribute("recipes", this.recipeService.getAllByOrderByServingsAsc()); break;
            case "servingsdesc": model.addAttribute("recipes", this.recipeService.getAllByOrderByServingsDesc()); break;

        }
        return "/admin/admin_index";
    }

    @GetMapping("/admin/info")
    public String info() {
        return "/admin/admin_info";
    }

    @RequestMapping(value = "/exit")
    public String exit() {
        return "redirect:/";
    }


    @RequestMapping(value = "/check")
    public String info(@RequestParam("login")String login, @RequestParam("password")String password) {
        if (this.adminService.checkEnter(login, password)) {
            return "redirect:/admin/";
        } else {
            return "info";
        }
    }
}
