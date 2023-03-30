package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.repository.IMiddleCategoryRepository;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.dto.ResponseMiddleCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class MiddleCategoryServiceImpl implements IMiddleCategoryService{
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public void addMiddleCategory(RequestMiddleCategory requestMiddleCategory) {
        MiddleCategory middle = MiddleCategory.builder()
                .name(requestMiddleCategory.getName())
                .mainCategory(MainCategory.builder()
                        .id(requestMiddleCategory.getMainCategoryId())
                        .build())
                .build();
        iMiddleCategoryRepository.save(middle);
    }
   @Override
   public void updateMiddleCategory(MiddleCategory middleCategory){
       MiddleCategory middleCategory1 = iMiddleCategoryRepository.findById(middleCategory.getId()).get();
       middleCategory1.setName(middleCategory.getName());
       iMiddleCategoryRepository.save(middleCategory1);
   }

    @Override
    public List<ResponseMiddleCategory> getAllMiddleCategory() {
        List<MiddleCategory> middleCategories = iMiddleCategoryRepository.findAll();
        List<ResponseMiddleCategory> responseMiddleCategories = new ArrayList<>();
        List<ResponseMiddleCategory.Data> data = new ArrayList<>();
        Integer mainIdx = 1;
        String name = middleCategories.get(0).getMainCategory().getName();
        for (MiddleCategory middleCategory : middleCategories) {
            if (mainIdx != middleCategory.getMainCategory().getId()) {
                responseMiddleCategories.add(ResponseMiddleCategory.builder()
                        .id(mainIdx)
                        .name(name)
                        .data(data)
                        .build());
                mainIdx = middleCategory.getMainCategory().getId();
                name = middleCategory.getMainCategory().getName();
                data = new ArrayList<>();
            }
            data.add(ResponseMiddleCategory.Data.builder()
                    .id(middleCategory.getId())
                    .name(middleCategory.getName())
                    .key("subCategory")
                    .build());
        }
        responseMiddleCategories.add(ResponseMiddleCategory.builder()
                .id(mainIdx)
                .name(name)
                .data(data)
                .build());
        return responseMiddleCategories;
    }
}
