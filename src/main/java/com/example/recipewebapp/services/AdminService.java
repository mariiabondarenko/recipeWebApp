package com.example.recipewebapp.services;

import com.example.recipewebapp.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public void setRepository(AdminRepository repository){
        this.adminRepository = repository;
    }

    public boolean checkEnter(String login, String password) {
        return this.adminRepository.findAll()
                                    .stream()
                                    .anyMatch(admin -> admin.getLogin().equals(login) && admin.getPassword().equals(password));
    }
}

