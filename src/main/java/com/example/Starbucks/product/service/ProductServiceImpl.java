package com.example.Starbucks.product.service;

import com.example.Starbucks.category.dto.ResponseSearch;
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
public class    ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;

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
    public List<Object> getAllProduct(int pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
        String key = "ProductAll:" + pageNum;
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if (redisTemplate.opsForList().size(key) == 0) {
            iProductRepository.getAllProduct(pageable).stream()
                    .map(element -> redisTemplate.opsForList().rightPush(key, ResponseSearch.builder()
                            .productId(element.getId())
                            .productName(element.getName())
                            .price(element.getPrice())
                            .thumbnail(element.getThumbnail())
                            .build())).collect(Collectors.toList());
        }
        redisTemplate.expire(key, Redis.EXPIRE_LIMIT.getValue(), TimeUnit.SECONDS);
        return redisTemplate.opsForList().range(key, 0, -1);
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
