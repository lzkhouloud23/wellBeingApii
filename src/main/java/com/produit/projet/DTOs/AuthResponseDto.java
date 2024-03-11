package com.produit.projet.DTOs;




import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private String password;


    public AuthResponseDto(String accessToken, String username, String password) {
        this.accessToken = accessToken;
        this.username = username;
        this.password = password;
    }
}


