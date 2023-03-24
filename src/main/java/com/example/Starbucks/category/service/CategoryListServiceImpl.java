package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.PageValue;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.repository.IMiddleCategoryRepository;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.vo.RequestCategoryList;
import com.example.Starbucks.config.RedisRepositoryConfig;
import com.example.Starbucks.enums.PageNum;
import com.example.Starbucks.enums.Redis;
import com.example.Starbucks.product.repository.IProductRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {
    private final CategoryListRepository categoryListRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;
    private final IProductRepository iProductRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if (middleCategoryId == null) {
            String key = "category:" + mainCategoryId + ":" + pageNum;
            if (redisTemplate.opsForList().size(key) == 0) {
                executeCache(key, categoryListRepository.searchByMainCategory(mainCategoryId, pageable), redisTemplate);
            }
        }
        String key = "category:" + mainCategoryId + "-" + middleCategoryId + ":" + pageNum;
        if (redisTemplate.opsForList().size(key) == 0) {
            executeCache(key, categoryListRepository.searchByCategories(mainCategoryId, middleCategoryId, pageable), redisTemplate);
        }
        return getCache(key);
    }

    @Override
    public ResponsePage searchCache(String keyword, int pageNum, Pageable pageable) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        String key = keyword + ":" + pageNum;
        if (redisTemplate.opsForList().size(key) == 0) {
            pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
            executeCache(key, categoryListRepository.searchKeyword(keyword, pageable), redisTemplate);
        }
        return getCache(key);
    }

    @Override
    public void executeCache(String key, Page<IProduct> products, RedisTemplate<Object, Object> redisTemplate) {
        if (redisTemplate.opsForList().size(key) == 0) {
            products.getContent().stream()
                    .map(element -> redisTemplate.opsForList().rightPush(key, ResponseSearch.builder()
                            .productId(element.getId())
                            .productName(element.getName())
                            .price(element.getPrice())
                            .thumbnail(element.getThumbnail())
                            .build())).collect(Collectors.toList());
        }
        RedisTemplate<Object, Object> redisTemplate2 = redisRepositoryConfig.pageTemplate();
        redisTemplate2.opsForList().rightPush(key + ":Page", PageValue.builder()
                .pageNum(products.getNumber())
                .pageSize(products.getSize())
                .totalElements(products.getTotalElements())
                .totalPage(products.getTotalPages())
                .build());
        redisTemplate2.expire(key + ":Page", Redis.EXPIRE_LIMIT.getValue(), TimeUnit.SECONDS);
        redisTemplate.expire(key, Redis.EXPIRE_LIMIT.getValue(), TimeUnit.SECONDS);
    }

    @Override
    public ResponsePage getCache(String key) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        RedisTemplate<Object, Object> redisTemplate2 = redisRepositoryConfig.pageTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PageValue pageValue = mapper.convertValue(redisTemplate2.opsForList().range(key + ":Page", 0, -1).get(0), PageValue.class);

        return ResponsePage.builder()
                .content(redisTemplate.opsForList().range(key, 0, -1))
                .totalPage(pageValue.getTotalPage())
                .pageNum(pageValue.getPageNum())
                .pageSize(pageValue.getPageSize())
                .totalElements(pageValue.getTotalElements())
                .build();
    }

    @Override
    public CategoryList addCategoryList(RequestCategoryList requestCategoryList) {
        if (requestCategoryList.getMiddleCategoryId() == null) {
            CategoryList categoryList = CategoryList.builder()
                    .mainCategory(mainCategoryRepository.findById(requestCategoryList.getMainCategoryId()).get())
                    .product(iProductRepository.findById(requestCategoryList.getProductId()).get())
                    .build();
            categoryListRepository.save(categoryList);
            return categoryList;
        }
        CategoryList categoryList = CategoryList.builder()
                .mainCategory(mainCategoryRepository.findById(requestCategoryList.getMainCategoryId()).get())
                .middleCategory(iMiddleCategoryRepository.findById(requestCategoryList.getMiddleCategoryId()).get())
                .product(iProductRepository.findById(requestCategoryList.getProductId()).get())
                .build();
        categoryListRepository.save(categoryList);
        return categoryList;
    }

}
