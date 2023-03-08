package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.vo.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainCategoryImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public List<ResponseMainCategory> getAllMainCategories() {
        List<MainCategory> mainCategories = mainCategoryRepository.findAll();
        List<ResponseMainCategory> responseMainCategoryList = new ArrayList<>();
        for (MainCategory mainCategory : mainCategories) {
            ResponseMainCategory responseMainCategory = ResponseMainCategory.builder()
                    .id(mainCategory.getId())
                    .name(mainCategory.getName())
                    .build();
            responseMainCategoryList.add(responseMainCategory);
        }

        return responseMainCategoryList;
    }

    @Override
    public MainCategory addMainCategories(MainCategory mainCategory) {
        return mainCategoryRepository.save(mainCategory);
    }
}