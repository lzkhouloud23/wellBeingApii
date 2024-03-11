package com.produit.projet.services;

import com.produit.projet.DTOs.ProductDto;
import com.produit.projet.DTOs.PromoDto;
import com.produit.projet.models.Product;
import com.produit.projet.models.Promotion;
import com.produit.projet.repositories.ProductRepo;
import com.produit.projet.repositories.PromoRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PromoService {

    @Autowired
    private final ModelMapper modelMapper ;
    private final PromoRepo promoRepo;

    public PromoService(ModelMapper modelMapper, PromoRepo promoRepo) {
        this.modelMapper = modelMapper;
        this.promoRepo = promoRepo;
    }

    public Optional<PromoDto> findPromotionById(Long promotionId){
        if (promotionId==0){}
        Optional<Promotion> promotion=promoRepo.findById(promotionId);
        return promotion.map(p->convertToDto(p));
    }
    public List<PromoDto> findAllPromotions() {
        List<Promotion> promotions = promoRepo.findAll();
        return promotions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public PromoDto addPromotion(PromoDto PromoDto) {
        Promotion promotion = modelMapper.map(PromoDto, Promotion.class);
        Promotion savedPromotion = promoRepo.save(promotion);
        return convertToDto(savedPromotion);
    }


    public void DeletePromotionById(long promotionId) {
        if (promotionId == 0) {

        }
        promoRepo.deleteById(promotionId);
    }
    private PromoDto convertToDto(Promotion promotion) {
        PromoDto promoDto = modelMapper.map(promotion, PromoDto.class);
        return promoDto;
    }


}
