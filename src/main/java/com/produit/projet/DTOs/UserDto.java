package com.produit.projet.DTOs;


import com.produit.projet.models.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();
}

