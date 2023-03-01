package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.CategoryList;
import com.example.Starbucks.product.model.MainCategory;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.CategoryListRepository;
import com.example.Starbucks.product.repository.MainCategoryRepository;
import com.example.Starbucks.product.vo.RequestCategory;
import com.example.Starbucks.product.vo.ResponseCategoryList;
import com.example.Starbucks.product.vo.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {


    private final MainCategoryRepository mainCategoryRepository;
    private final CategoryListRepository categoryListRepository;


    @Override
    public void addCategory(RequestCategory requestCategory) {
        ModelMapper modelMapper = new ModelMapper();
        MainCategory mainCategory = modelMapper.map(requestCategory, MainCategory.class);
        mainCategoryRepository.save(mainCategory);
    }

    @Override
    public MainCategory getCategory(Integer categoryId) {
        return mainCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<MainCategory> getAll() {
        return mainCategoryRepository.findAll();
    }

    /*@Override
    public List<CategoryTypeDto> getAllType(String categoryType) {
        ModelMapper modelMapper = new ModelMapper();
        List<Category> categories = iCategoryRepository.findAllByType(categoryType);
        List<CategoryTypeDto> categoryTypeDtoList = new ArrayList<>();

        categories.forEach(category -> {
            categoryTypeDtoList.add (modelMapper.map(category,CategoryTypeDto.class));

        });
        return null;
    }*/
    @Override
    public void updateCategory(MainCategory mainCategory){

        MainCategory mainCategory1 = mainCategoryRepository.findById(mainCategory.getId()).get();
        mainCategory1.setName(mainCategory.getName());

        mainCategoryRepository.save(mainCategory1);
    }

    @Override
    public List<ResponseCategoryList.categorySearchInfo> searchByCategory(Integer mainCategoryId, Integer middleCategoryId) {
        List<CategoryList> categories = categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId);
        List<ResponseCategoryList.categorySearchInfo> responseSearchInfo = new ArrayList<>();
        for (CategoryList categoryList : categories) {
            Product product = categoryList.getProduct();
            ResponseCategoryList.categorySearchInfo searchInfo = ResponseCategoryList.categorySearchInfo.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .build();
            responseSearchInfo.add(searchInfo);
        }

        return responseSearchInfo;
    }

    /*@Override
    public List<String> getCategoryTypeName() {
        return iCategoryRepository.groupByType();
    }*/


}