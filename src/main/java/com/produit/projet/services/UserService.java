package com.produit.projet.services;

import com.produit.projet.models.User;
import com.produit.projet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        repository.save(user);
    }




    public List<User> findAll(){
        return repository.findAll();
    }
}
