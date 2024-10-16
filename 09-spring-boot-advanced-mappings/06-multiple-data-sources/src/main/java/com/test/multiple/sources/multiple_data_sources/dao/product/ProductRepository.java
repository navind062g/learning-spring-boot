package com.test.multiple.sources.multiple_data_sources.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.multiple.sources.multiple_data_sources.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
