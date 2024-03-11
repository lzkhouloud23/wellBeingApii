package com.produit.projet.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PromoDto {
    private Long id;
    private String name;
    private String discount;
    private Date startdate;
    private Date enddate;
    private String imagepromotion;
    private double newprice ;
}
