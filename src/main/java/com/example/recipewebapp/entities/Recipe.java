package com.example.recipewebapp.entities;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;


@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nameRecipe")
    @NotEmpty(message = "Не должно быть пустым")
    private String name;
    private Integer servings;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    @NotEmpty(message = "Не должно быть пустым")
    @Type(type = "org.hibernate.type.TextType")
    private String ingredients;
    @Lob
    @NotEmpty(message = "Не должно быть пустым")
    @Type(type = "org.hibernate.type.TextType")
    private String directions;
    @Enumerated(EnumType.STRING)
    private Category category;

    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(MultipartFile image) throws IOException {
        this.image = image.getBytes();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Recipe() {
    }

    public Recipe(String name, Integer servings, String ingredients, String directions, Category category, MultipartFile image) throws IOException {
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
        setImage(image);
    }


}
