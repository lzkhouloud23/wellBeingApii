package com.produit.projet.controllers;


import com.produit.projet.DTOs.PromoDto;
import com.produit.projet.services.PromoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promotion")
@CrossOrigin(origins = "http://localhost:4200")
public class PromotionController {

    private final PromoService promoService ;

    public PromotionController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromoDto>> getAllPromotions(){
        List<PromoDto> promoDto= promoService.findAllPromotions();
        return new ResponseEntity<>(promoDto, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<PromoDto> getPromotionById( @PathVariable("id")Long promotionId){
        Optional<PromoDto> promoDto= promoService.findPromotionById(promotionId);
        if (promoDto.isPresent()) {
            return new ResponseEntity<>(promoDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<PromoDto> addPromotion(@RequestBody PromoDto promoDto, HttpServletResponse response){
        PromoDto newPromo =promoService.addPromotion(promoDto)  ;
        return new ResponseEntity<>(newPromo,HttpStatus.CREATED );
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> DeletePromo(@PathVariable("id") Long id) {
        promoService.DeletePromotionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
