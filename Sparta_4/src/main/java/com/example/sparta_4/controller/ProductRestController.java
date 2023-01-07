package com.example.sparta_4.controller;

import com.example.sparta_4.domain.Product;
import com.example.sparta_4.dto.ProductRequestDto;
import com.example.sparta_4.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductRepository productRepository;

    //등록된 전체 상품 조회
    @GetMapping("/api/products")
    public List<Product> getProduct(){

        return productRepository.findAll();
    }


    //신규 상품 등록
    @PostMapping("api/products")
    public Product createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product = new Product(productRequestDto); //Product 테이블에 객체(필드)가 하나씩 쌓이는 것일까?
        productRepository.save(product);
        return product;
    }

}
