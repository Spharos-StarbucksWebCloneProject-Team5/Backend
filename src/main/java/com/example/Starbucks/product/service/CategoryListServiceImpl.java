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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    @Override
    public void updateCategory(MainCategory mainCategory) {

        MainCategory mainCategory1 = mainCategoryRepository.findById(mainCategory.getId()).get();
        mainCategory1.setName(mainCategory.getName());

        mainCategoryRepository.save(mainCategory1);
    }

    @Override
    public Page<ResponseCategoryList.categorySearchInfo> searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Pageable pageable) {
        Page<CategoryList> categories = categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId, pageable);
        return categoryListToSearchInfoMapper(categories);
    }

    @Override
    public Page<ResponseCategoryList.categorySearchInfo> searchByNameOrDescription(String keyword, String keyword2, Pageable pageable) {
        Page<CategoryList> categories = categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword2, pageable);
        return categoryListToSearchInfoMapper(categories);
    }

    public Page<ResponseCategoryList.categorySearchInfo> categoryListToSearchInfoMapper(Page<CategoryList> categories) {
        Page<ResponseCategoryList.categorySearchInfo> responseSearchInfo = categories.map
                (new Function<CategoryList, ResponseCategoryList.categorySearchInfo>() {
            @Override
            public ResponseCategoryList.categorySearchInfo apply(CategoryList categoryList) {
                Product product = categoryList.getProduct();
                ResponseCategoryList.categorySearchInfo searchInfo = ResponseCategoryList.categorySearchInfo.builder()
                        .productId(product.getId())
                        .productName(product.getName())
                        .price(product.getPrice())
                        .thumbnail(product.getThumbnail())
                        .build();
                return searchInfo;
            }
        });
        return responseSearchInfo;
    }


}
