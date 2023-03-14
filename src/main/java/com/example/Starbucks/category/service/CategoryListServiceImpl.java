package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.repository.CategoryListRepository;
import com.example.Starbucks.category.dto.ResponsePage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryListServiceImpl implements ICategoryListService {

    private final static int PAGE_SIZE = 8;
    private final CategoryListRepository categoryListRepository;

    @Override
    public ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryId, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        if (middleCategoryId == 0) {
            return getPageInfo(categoryListRepository.findAllByMainCategoryId(mainCategoryId, pageable));
        } else {
            return getPageInfo(categoryListRepository.findAllByMainCategoryIdAndMiddleCategoryId(mainCategoryId, middleCategoryId, pageable));
        }
    }

    @Override
    public ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PAGE_SIZE);
        return getPageInfo(categoryListRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword2, pageable));
    }

    public ResponsePage getPageInfo(Page<CategoryList> categories) {
        return ResponsePage.builder()
                .pageSize(categories.getSize())
                .pageNum(categories.getNumber())
                .totalPage(categories.getTotalPages())
                .totalElements(categories.getTotalElements())
                .content(ResponsePage.ofContents(categories))
                .build();
    }

}
