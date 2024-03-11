package com.produit.projet.controllers;

import com.produit.projet.DTOs.ProductDto;
import com.produit.projet.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductController {
    private final ProductService productService ;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto= productService.findAllProducts();
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ProductDto> getProductById( @PathVariable("id")Long productId){
        Optional<ProductDto> productDto= productService.findProductById(productId);
        if (productDto.isPresent()) {
            return new ResponseEntity<>(productDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    //@PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, HttpServletResponse response){
        ProductDto newProduct =productService.addProduct(productDto)  ;
        return new ResponseEntity<>(newProduct,HttpStatus.CREATED );
    }
    @PutMapping("/update")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> UpdateProduct(@RequestBody ProductDto productDto , HttpServletResponse response) {
        ProductDto updateProduct= productService.UpdateProduct(productDto);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> DeleteProduct(@PathVariable("id") Long id) {
        productService.DeleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}