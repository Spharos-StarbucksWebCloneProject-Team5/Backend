package com.teamSiHyun.Starbucks.api.category.service;

import com.teamSiHyun.Starbucks.api.category.model.MainCategory;
import com.teamSiHyun.Starbucks.api.category.repository.MainCategoryRepository;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MainCategoryImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public List<ResponseMainCategory.Side> getAllSideMainCategories() {
        return mainCategoryRepository.findAll().stream()
                .map(element -> ResponseMainCategory.Side.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .thumbNail(element.getThumbNail())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<ResponseMainCategory> getAllMainCategories() {
        List<ResponseMainCategory> responseMainCategories = mainCategoryRepository.findAll().stream()
                .map(element -> ResponseMainCategory.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .key("category")
                        .build()).collect(Collectors.toList());
        responseMainCategories.add(0, ResponseMainCategory.builder()
                        .id(0)
                        .name("전체")
                        .key("category")
                .build());
        return responseMainCategories;
    }

    @Override
    public MainCategory addMainCategories(MainCategory mainCategory) {
        return mainCategoryRepository.save(mainCategory);
    }
}
