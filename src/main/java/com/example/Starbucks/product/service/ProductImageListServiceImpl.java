package com.example.Starbucks.product.service;


import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.product.repository.IProductImageListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductImageListServiceImpl implements IProductImageListService{

    private final IProductImageListRepository iProductImageListRepository;
    @Override
    public void addProductImage(ProductImageList productImageList) {
        iProductImageListRepository.save(productImageList);
    }

    @Override
    public List<ProductImageList> getByProductId(Long productId) {
        return iProductImageListRepository.findAllByProductId(productId);
    }

    @Override
    public List<ProductImageList> getAll() {
        return iProductImageListRepository.findAll();
    }


    @Override
    public void updateProductImageList(ProductImageList productImageList){

        ProductImageList productImageList1 = iProductImageListRepository.findById(productImageList.getId()).get();
        productImageList1.setImage(productImageList.getImage());



        iProductImageListRepository.save(productImageList1);
    }
}
