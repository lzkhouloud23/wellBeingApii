package com.produit.projet.repositories;

import com.produit.projet.models.Product;
import com.produit.projet.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepo extends JpaRepository<Promotion,Long> {
}
