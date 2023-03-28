package com.example.Starbucks.product.service;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.service.CategoryListServiceImpl;
import com.example.Starbucks.category.service.ICategoryListService;
import com.example.Starbucks.config.RedisRepositoryConfig;
import com.example.Starbucks.enums.PageNum;
import com.example.Starbucks.enums.Redis;
import com.example.Starbucks.product.dto.ResponseProductList;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
    public ResponsePage getAllProduct(int pageNum, Pageable pageable) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        RedisTemplate<Object, Object> redisTemplate2 = redisRepositoryConfig.pageTemplate();
        String key = "ProductAll:" + pageNum;
        log.info("ProductAll: " + iProductRepository.getAllProduct(pageable).getContent().get(0).getMain_Category_Id());
        log.info("ProductAll: " + iProductRepository.getAllProduct(pageable).getContent().get(0).getMiddle_Category_Id());
        if (redisTemplate.opsForList().size(key) == 0) {
            pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
            iCategoryListService.executeCache(key, iProductRepository.getAllProduct(pageable), redisTemplate);
        }
        return iCategoryListService.getCache(key);
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
