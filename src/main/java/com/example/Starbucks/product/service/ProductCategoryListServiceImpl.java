package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.ProductCategoryList;
import com.example.Starbucks.product.repository.ICategoryRepository;
import com.example.Starbucks.product.repository.IProductCategoryListRepository;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.vo.RequestProductCategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService{

    private final IProductRepository iProductRepository;
    private final ICategoryRepository iCategoryRepository;
    private final IProductCategoryListRepository iProductCategoryListRepository;

    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {
        iProductCategoryListRepository.save(ProductCategoryList.builder()
                .category(iCategoryRepository.findById(requestProductCategoryList.getCategoryId()).get())
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .build()
        );
    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }
}
