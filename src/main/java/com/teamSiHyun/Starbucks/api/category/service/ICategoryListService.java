package com.teamSiHyun.Starbucks.api.category.service;

import com.teamSiHyun.Starbucks.api.category.dto.ResponsePage;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseSearch;
import com.teamSiHyun.Starbucks.api.category.model.CategoryList;
import com.teamSiHyun.Starbucks.api.category.projection.IProduct;
import com.teamSiHyun.Starbucks.api.category.vo.RequestCategoryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer category, Integer subCategory, Integer pageNum, Pageable pageable);
    ResponsePage searchCache(String keyword, int pageNum, Pageable pageable);
    List<ResponseSearch> searchCache2(String keyword, Long lastProductId);
    CategoryList addCategoryList(RequestCategoryList requestCategoryList);
    void executeCache(String key, Page<IProduct> products, RedisTemplate<Object, Object> redisTemplate);
    ResponsePage getCache(String key);
}
