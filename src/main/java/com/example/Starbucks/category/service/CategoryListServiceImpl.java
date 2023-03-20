package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.ProductList;
import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.repository.IMiddleCategoryRepository;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.repository.SearchRepository;
import com.example.Starbucks.category.vo.RequestCategoryList;
import com.example.Starbucks.config.RedisRepositoryConfig;
import com.example.Starbucks.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {

    private final static int PAGE_SIZE = 8;
    private final CategoryListRepository categoryListRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;
    private final SearchRepository searchRepository;
    private final IProductRepository iProductRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;
    @Override
    public ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        if (middleCategoryId == 0) {
            return getPageInfo(categoryListRepository.findAllByMainCategoryId(mainCategoryId, pageable));
        } else {
            return getPageInfo(categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId, pageable));
        }
    }

    @Override
    public ResponsePage searchByNameOrDescription(String keyword, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        return getPageInfo2(categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, pageable));
    }

    @Override
    public List<Object> searchCache(String keyword) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if(redisTemplate.opsForList().size(keyword) == 0) {
            categoryListRepository.searchKeyword(keyword).stream()
                    .map(element -> redisTemplate.opsForList().rightPush(keyword, ResponseSearch.builder()
                            .productId(element.getId())
                            .productName(element.getName())
                            .price(element.getPrice())
                            .thumbnail(element.getThumbnail())
                            .build())).collect(Collectors.toList());
        }
        return redisTemplate.opsForList().range(keyword,0,-1);
    }

    @Override
    public List<ProductList> searchCache2(String keyword) {
        RedisTemplate<Object, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if(searchRepository.count() == 0) {
//            searchRepository.saveAll(categoryListRepository.searchKeyword(keyword));
//            categoryListRepository.searchKeyword(keyword).stream()
//                    .map(element -> searchRepository.save(ProductList.builder()
//                            .productId(element.getId())
//                            .productName(element.getName())
//                            .price(element.getPrice())
//                            .thumbnail(element.getThumbnail())
//                            .build())).collect(Collectors.toList());
        }
        List<ProductList> productLists = new ArrayList<>();
        log.info(searchRepository.findAll().toString());
        searchRepository.findAll().forEach(productList -> productLists.add(ProductList.builder()
                        .id(productList.getId())
                        .productId(productList.getProductId())
                        .price(productList.getPrice())
                        .productName(productList.getProductName())
                        .thumbnail(productList.getThumbnail())
                .build()));
        return productLists;
    }

    @Override
    public CategoryList addCategoryList(RequestCategoryList requestCategoryList) {
        CategoryList categoryList =  CategoryList.builder()
                .mainCategory(mainCategoryRepository.findById(requestCategoryList.getMainCategoryId()).get())
                .middleCategory(iMiddleCategoryRepository.findById(requestCategoryList.getMiddleCategoryId()).get())
                .product(iProductRepository.findById(requestCategoryList.getProductId()).get())
                .build();
        categoryListRepository.save(categoryList);
        return categoryList;
    }

    public ResponsePage getPageInfo(Page<CategoryList> categories) {
        return ResponsePage.builder()
                .pageSize(categories.getSize())
                .pageNum(categories.getNumber())
                .totalPage(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .content(ResponsePage.ofContents(categories))
                .build();
    }

    public ResponsePage getPageInfo2(Page<IProduct> categories) {
        return ResponsePage.builder()
                .pageSize(categories.getSize())
                .pageNum(categories.getNumber())
                .totalPage(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .content(ResponsePage.ofContents2(categories))
                .build();
    }

}
