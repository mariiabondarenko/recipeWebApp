package com.example.recipewebapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "videoRecipes")
public class VideoRecipe {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nameVideoRecipe")
    @NotEmpty(message = "Не должно быть пустым")
    private String name;

    @NotEmpty(message = "Не должно быть пустым")
    private String path;

    public VideoRecipe() {
    }

    public VideoRecipe(@NotEmpty(message = "Не должно быть пустым") String name, @NotEmpty(message = "Не должно быть пустым") String path) {
        this.name = name;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
