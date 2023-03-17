package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.config.RedisRepositoryConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {

    private final static int PAGE_SIZE = 8;
    private final CategoryListRepository categoryListRepository;
    private final RedisRepositoryConfig redisRepositoryConfig;
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
    public ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable) {
        RedisTemplate<String, Object> redisTemplate = redisRepositoryConfig.redisTemplate();
        ModelMapper modelMapper = new ModelMapper();
        ObjectMapper mapper = new ObjectMapper();
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        try {
            return mapper.convertValue(redisTemplate.opsForValue().get(keyword), ResponsePage.class);
        } catch(Exception e) {
            ResponsePage responsePage = getPageInfo(categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword2, pageable));
            redisTemplate.opsForValue().set(keyword, String.valueOf(responsePage));
            return responsePage;
        }
    }

    @Override
    public List<Object> searchCache(String keyword) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        RedisTemplate<String, Object> redisTemplate = redisRepositoryConfig.searchRedisTemplate();
        if(redisTemplate.opsForList().size(keyword) == 0) {
            categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword).stream()
                    .map(element -> redisTemplate.opsForList().rightPush(keyword, ResponseSearch.builder()
                                            .productId(element.getId())
                                            .productName(element.getName())
                                            .price(element.getPrice())
                                            .thumbnail(element.getThumbnail())
                            .build())).collect(Collectors.toList());
        }
        return redisTemplate.opsForList().range(keyword,0,-1);
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

}
