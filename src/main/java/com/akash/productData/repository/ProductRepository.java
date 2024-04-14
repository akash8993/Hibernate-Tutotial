package com.akash.productData.repository;

import com.akash.productData.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByName(String name);

    public List<Product> findByNameAndDescription(String name, String description);

    public List<Product> findByPriceGreaterThan(Double price);

    public List<Product> findByDescriptionContains(String desc);

    public List<Product> findByPriceBetween(Double price1, Double price2);

    public List<Product> findByDescriptionLike(String desc);

    public List<Product> findByIdIn(List<Integer> ids);
}
