package com.teamSiHyun.Starbucks.api.product.service;

import com.teamSiHyun.Starbucks.api.category.service.ICategoryListService;
import com.teamSiHyun.Starbucks.api.product.model.Product;
import com.teamSiHyun.Starbucks.api.product.vo.RequestProduct;
import com.teamSiHyun.Starbucks.api.product.vo.ResponseProduct;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseSearch;
import com.teamSiHyun.Starbucks.config.RedisRepositoryConfig;
import com.teamSiHyun.Starbucks.api.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;
    private final ICategoryListService iCategoryListService;

    @Override
    public void addProduct(RequestProduct requestProduct) {

        Product product = Product.builder()
                .name(requestProduct.getName())
                .price(requestProduct.getPrice())
                .description(requestProduct.getDescription())
                .thumbnail(requestProduct.getThumbnail())
                .count(requestProduct.getCount())
                .build();
        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long productId) {
        Product product = iProductRepository.findById(productId).get();
        ResponseProduct responseProduct = ResponseProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .count(product.getCount())
                .build();
        return responseProduct;
    }

    @Override
    public List<ResponseSearch> getAllProduct2(Long productId) {
        return iProductRepository.getAllProduct(productId).stream().map(
                element-> ResponseSearch.builder()
                        .productId(element.getId())
                        .productName(element.getName())
                        .price(element.getPrice())
                        .thumbnail(element.getThumbnail())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, RequestProduct requestProduct){
        Product product = Product.builder()
                .id(id)
                .name(requestProduct.getName())
                .price(requestProduct.getPrice())
                .description(requestProduct.getDescription())
                .thumbnail(requestProduct.getThumbnail())
                .count(requestProduct.getCount())
                .build();


        iProductRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        Product product = iProductRepository.findById(id).get();
        iProductRepository.delete(product);
    }
}
