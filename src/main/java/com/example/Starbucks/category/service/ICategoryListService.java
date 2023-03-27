package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.category.vo.RequestCategoryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);
    ResponsePage searchCache(String keyword, int pageNum, Pageable pageable);
    List<ResponseSearch> searchCache2(String keyword, Long lastProductId);
    CategoryList addCategoryList(RequestCategoryList requestCategoryList);
    void executeCache(String key, Page<IProduct> products, RedisTemplate<Object, Object> redisTemplate);
    ResponsePage getCache(String key);
}
