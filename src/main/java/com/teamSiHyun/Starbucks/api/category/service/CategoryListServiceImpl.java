package com.teamSiHyun.Starbucks.api.category.service;

import com.teamSiHyun.Starbucks.api.category.dto.PageValue;
import com.teamSiHyun.Starbucks.api.category.dto.ResponsePage;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseSearch;
import com.teamSiHyun.Starbucks.api.category.model.CategoryList;
import com.teamSiHyun.Starbucks.api.category.projection.IProduct;
import com.teamSiHyun.Starbucks.api.category.repository.CategoryListRepository;
import com.teamSiHyun.Starbucks.api.category.repository.IMiddleCategoryRepository;
import com.teamSiHyun.Starbucks.api.category.repository.MainCategoryRepository;
import com.teamSiHyun.Starbucks.api.category.vo.RequestCategoryList;
import com.teamSiHyun.Starbucks.api.product.repository.IProductRepository;
import com.teamSiHyun.Starbucks.config.RedisRepositoryConfig;
import com.teamSiHyun.Starbucks.enums.PageNum;
import com.teamSiHyun.Starbucks.enums.Redis;
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
    public ResponsePage searchByCategory(Integer category, Integer subCategory, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if (category == 0) {
            String key = "ProductAll:" + pageNum;
            if (redisTemplate.opsForList().size(key) == 0) {
                executeCache(key, iProductRepository.getAllProduct(pageable), redisTemplate);
            }
            return getCache(key);
        }
        if (subCategory == null) {
            String key = "category:" + category + ":" + pageNum;
            if (redisTemplate.opsForList().size(key) == 0) {
                executeCache(key, categoryListRepository.searchByMainCategory(category, pageable), redisTemplate);
            }
            return getCache(key);
        }
        String key = "category:" + category + "-" + subCategory + ":" + pageNum;
        if (redisTemplate.opsForList().size(key) == 0) {
            executeCache(key, categoryListRepository.searchByCategories(category, subCategory, pageable), redisTemplate);
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
    public List<ResponseSearch> searchCache2(String keyword, Long lastProductId) {
        return categoryListRepository.searchKeyword2(keyword, lastProductId).stream()
                .map(iProduct -> ResponseSearch.builder()
                        .productId(iProduct.getId())
                        .productName(iProduct.getName())
                        .price(iProduct.getPrice())
                        .thumbnail(iProduct.getThumbnail())
                        .build()).collect(Collectors.toList());
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
                            .mainCategoryId(element.getMain_Category_Id())
                            .middleCategoryId(element.getMiddle_Category_Id())
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
