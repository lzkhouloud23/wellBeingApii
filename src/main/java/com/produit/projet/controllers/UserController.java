package com.produit.projet.controllers;

import com.produit.projet.DTOs.AuthResponseDto;
import com.produit.projet.DTOs.UserDto;
import com.produit.projet.configg.JWTGenerator;
import com.produit.projet.models.Role;
import com.produit.projet.models.User;
import com.produit.projet.repositories.RoleRepository;
import com.produit.projet.repositories.UserRepository;
import com.produit.projet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final RoleRepository roleRepository;





    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // messagingTemplate.convertAndSend("/user/public", "Nouvel utilisateur connecté: " + user.getUsername());


        Optional<User> user1 = userRepository.findByUsername(user.getUsername());
        String token = jwtGenerator.generateToken(authentication);
        AuthResponseDto authResponseDTO = new AuthResponseDto(
                token,
                user1.get().getUsername(),
                user1.get().getPassword());

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode((userDto.getPassword())));
        //user.setUsername(userDto.getUsername());
        //user.setPassword(userDto.getPassword());
        //Role roles = roleRepository.findByName("USER").get();
      //user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
