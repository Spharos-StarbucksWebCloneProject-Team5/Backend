package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;

    @Override
    public void addProduct(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return iProductRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return iProductRepository.findAll();
    }
}
