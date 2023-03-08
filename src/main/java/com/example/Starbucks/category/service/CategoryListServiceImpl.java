package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.repository.MainCategoryRepository;
import com.example.Starbucks.category.vo.RequestCategory;
import com.example.Starbucks.category.vo.ResponseCategoryList;
import com.example.Starbucks.category.vo.ResponsePage;
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

    private final static int PAGE_SIZE = 8;

    private final MainCategoryRepository mainCategoryRepository;
    private final CategoryListRepository categoryListRepository;

    @Override
    public ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        Page<CategoryList> categories;
        if (middleCategoryId == 0) {
            categories = categoryListRepository.findAllByMainCategoryId(mainCategoryId, pageable);
        } else {
            categories = categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId, pageable);
        }
        return getPageInfo(categories);
    }

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
