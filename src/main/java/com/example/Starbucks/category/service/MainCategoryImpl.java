package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.dto.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MainCategoryImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public List<ResponseMainCategory> getAllMainCategories() {
        List<ResponseMainCategory> responseMainCategoryList = mainCategoryRepository.findAll().stream()
                .map(element -> ResponseMainCategory.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .build()).collect(Collectors.toList());
        return responseMainCategoryList;
    }

    @Override
    public MainCategory addMainCategories(MainCategory mainCategory) {
        return mainCategoryRepository.save(mainCategory);
    }
}
