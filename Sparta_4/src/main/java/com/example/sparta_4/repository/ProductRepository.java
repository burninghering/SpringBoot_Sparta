package com.example.sparta_4.repository;

import com.example.sparta_4.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
