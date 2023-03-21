package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.repository.IMiddleCategoryRepository;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.vo.RequestCategoryList;
import com.example.Starbucks.config.RedisRepositoryConfig;
import com.example.Starbucks.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {

    private final static int PAGE_SIZE = 8;
    private final static int CACHE_LIMIT = 20;
    private final CategoryListRepository categoryListRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;
    private final IProductRepository iProductRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public List<ResponseSearch> searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        List<ResponseSearch> responseSearches = new ArrayList<>();
        if (middleCategoryId == null) {
            return ResponseSearch.ofContents(categoryListRepository.searchByMainCategory(mainCategoryId, pageable));
        } else {
            return  ResponseSearch.ofContents(categoryListRepository.searchByCategories(mainCategoryId, middleCategoryId, pageable));
        }
    }

    @Override
    public List<Object> searchCache(String keyword, int pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        return addCache(keyword, pageNum, pageable);
    }

    public List<Object> addCache(String keyword, int pageNum, Pageable pageable) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if (redisTemplate.opsForList().size(keyword + ":" + pageNum) == 0) {
            categoryListRepository.searchKeyword(keyword, pageable).stream()
                    .map(element -> redisTemplate.opsForList().rightPush(keyword + ":" + pageNum, ResponseSearch.builder()
                            .productId(element.getId())
                            .productName(element.getName())
                            .price(element.getPrice())
                            .thumbnail(element.getThumbnail())
                            .build())).collect(Collectors.toList());
        }
        return redisTemplate.opsForList().range(keyword + ":" + pageNum, 0, -1);
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
