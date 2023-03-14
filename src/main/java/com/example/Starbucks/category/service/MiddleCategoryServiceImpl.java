package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.repository.IMiddleCategoryRepository;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.dto.ResponseMiddleCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class MiddleCategoryServiceImpl implements IMiddleCategoryService{
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public void addMiddleCategory(RequestMiddleCategory requestMiddleCategory) {
        MainCategory mainCategory = MainCategory.builder()
                .id(requestMiddleCategory.getMainCategoryId())
                .build();
        MiddleCategory middle = MiddleCategory.builder()
                .name(requestMiddleCategory.getName())
                .mainCategory(mainCategory)
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
        List<ResponseMiddleCategory> responseMiddleCategoryList = iMiddleCategoryRepository.findAll().stream()
                .map(element -> ResponseMiddleCategory.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .mainCategoryId(element.getMainCategory().getId())
                        .build()).collect(Collectors.toList());
        return responseMiddleCategoryList;
    }
}
