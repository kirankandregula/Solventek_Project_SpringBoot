package com.solventek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solventek.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
