package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.CategoryListRepository;
import com.example.Starbucks.product.repository.MainCategoryRepository;
import com.example.Starbucks.category.vo.RequestCategory;
import com.example.Starbucks.category.vo.ResponseCategoryList;
import com.example.Starbucks.product.vo.ResponsePage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {

    private final static int PAGE_SIZE = 3;

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
    public ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        Page<CategoryList> categories = categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId, pageable);
        return getPageInfo(categories);
    }

//    @Override
//    public Page<ResponseCategoryList.categorySearchInfo> searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable) {
//        pageable = PageRequest.of(pageNum, PAGE_SIZE);
//        Page<CategoryList> categories = categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword2, pageable);
//        return categoryListToSearchInfoMapper(categories);
//    }

    @Override
    public ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        Page<CategoryList> categories = categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword2, pageable);
        return getPageInfo(categories);
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

    public ResponsePage getPageInfo(Page<CategoryList> categories) {
        List<ResponsePage.Contents> contents = new ArrayList<>();
        for (CategoryList categoryList : categories.getContent()) {
            Product product = categoryList.getProduct();
            ResponsePage.Contents content = ResponsePage.Contents.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .build();
            contents.add(content);
        }
        ResponsePage responsePage = ResponsePage.builder()
                .pageSize(categories.getSize())
                .pageNum(categories.getNumber())
                .totalPage(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .content(contents)
                .build();
        return responsePage;
    }


}
