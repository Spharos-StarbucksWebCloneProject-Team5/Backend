package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.MainCategory;
import com.example.Starbucks.product.repository.MainCategoryRepository;
import com.example.Starbucks.product.vo.ResponseMainCategory;
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
}
