package com.example.Starbucks.product.service;

import com.example.Starbucks.product.ProductCategoryListSpecs;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.product.repository.MainCategoryRepository;
import com.example.Starbucks.product.repository.IMiddleCategoryRepository;
import com.example.Starbucks.product.repository.IProductCategoryListRepository;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.category.vo.RequestProductCategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements IProductCategoryListService {


    private final IProductCategoryListRepository iProductCategoryListRepository;

    private final IProductRepository iProductRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;
    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {

        CategoryList categoryList = CategoryList.builder()
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .mainCategory(mainCategoryRepository.findById(requestProductCategoryList.getCategoryId()).get())
                .middleCategory(iMiddleCategoryRepository.findById(requestProductCategoryList.getMiddleCategoryId()).get())
                .build();
        iProductCategoryListRepository.save(categoryList);
    }

    @Override
    public List<CategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }

    @Override
    public List<CategoryList> getByCategoryId(Integer categoryId) {
        return iProductCategoryListRepository.findAllById(categoryId);
    }
    @Override
    public List<CategoryList> getAll(){
        return iProductCategoryListRepository.findAll();
    }

    public List<CategoryList> getByMiddleCategoryId(Integer categoryId, Integer middleCategoryId) {
        Specification<CategoryList> categorySpec = ProductCategoryListSpecs.searchCategoryID(categoryId);
        String sMiddleCategoryId = Integer.toString(middleCategoryId);
        Specification<CategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(0) - '0');
        Specification<CategoryList> compositeSpec = categorySpec.and(middleCategorySpec);

        Specification<CategoryList> middleCategorySpec2;
        for (int i = 1; i < sMiddleCategoryId.length(); i++) {
            middleCategorySpec2 = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(i) - '0');
            compositeSpec = compositeSpec.or(middleCategorySpec2);
        }


        return iProductCategoryListRepository.findAll(compositeSpec);
    }

    public List<CategoryList> getCategory(Integer categoryId, Integer middleCategoryId) {
        Specification<CategoryList> categorySpec = ProductCategoryListSpecs.searchCategoryID(categoryId);
        Specification<CategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(middleCategoryId);
        /*Specification<ProductCategoryList> priceSpec = ProductCategoryListSpecs.searchPrice(price);
        Specification<ProductCategoryList> seasonSpec = ProductCategoryListSpecs.searchSeason(season);*/

        /*String sMiddleCategoryId = Integer.toString(middleCategoryId);
        Specification<ProductCategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(0) - '0');*/
        //Specification<ProductCategoryList> compositeSpec = categorySpec.and(middleCategorySpec).and(priceSpec).and(seasonSpec);
        Specification<CategoryList> compositeSpec = categorySpec.and(middleCategorySpec);



        return iProductCategoryListRepository.findAll(compositeSpec);
    }


}
