package com.example.Spring.Boot.Tutorial.repositories;

import com.example.Spring.Boot.Tutorial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
