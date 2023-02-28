package com.example.Starbucks.product.service;

import com.example.Starbucks.product.ProductCategoryListSpecs;
import com.example.Starbucks.product.model.ProductCategoryList;
import com.example.Starbucks.product.repository.ICategoryRepository;
import com.example.Starbucks.product.repository.IMiddleCategoryRepository;
import com.example.Starbucks.product.repository.IProductCategoryListRepository;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.vo.RequestProductCategoryList;
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
    private final ICategoryRepository iCategoryRepository;
    private final IMiddleCategoryRepository iMiddleCategoryRepository;
    @Override
    public void addProductCategoryList(RequestProductCategoryList requestProductCategoryList) {

        ProductCategoryList productCategoryList = ProductCategoryList.builder()
                .product(iProductRepository.findById(requestProductCategoryList.getProductId()).get())
                .category(iCategoryRepository.findById(requestProductCategoryList.getCategoryId()).get())
                .middleCategory(iMiddleCategoryRepository.findById(requestProductCategoryList.getMiddleCategoryId()).get())
                .build();
        iProductCategoryListRepository.save(productCategoryList);
    }

    @Override
    public List<ProductCategoryList> getByProductId(Long productId) {
        return iProductCategoryListRepository.findAllByProductId(productId);
    }

    @Override
    public List<ProductCategoryList> getByCategoryId(Integer categoryId) {
        return iProductCategoryListRepository.findAllByCategoryId(categoryId);
    }
    @Override
    public List<ProductCategoryList> getAll(){
        return iProductCategoryListRepository.findAll();
    }

    public List<ProductCategoryList> getByMiddleCategoryId(Integer categoryId, Integer middleCategoryId) {
        Specification<ProductCategoryList> categorySpec = ProductCategoryListSpecs.searchCategoryID(categoryId);
        String sMiddleCategoryId = Integer.toString(middleCategoryId);
        Specification<ProductCategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(0) - '0');
        Specification<ProductCategoryList> compositeSpec = categorySpec.and(middleCategorySpec);

        Specification<ProductCategoryList> middleCategorySpec2;
        for (int i = 1; i < sMiddleCategoryId.length(); i++) {
            middleCategorySpec2 = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(i) - '0');
            compositeSpec = compositeSpec.or(middleCategorySpec2);
        }


        return iProductCategoryListRepository.findAll(compositeSpec);
    }

    public List<ProductCategoryList> getCategory(Integer categoryId, Integer middleCategoryId) {
        Specification<ProductCategoryList> categorySpec = ProductCategoryListSpecs.searchCategoryID(categoryId);
        Specification<ProductCategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(middleCategoryId);
        /*Specification<ProductCategoryList> priceSpec = ProductCategoryListSpecs.searchPrice(price);
        Specification<ProductCategoryList> seasonSpec = ProductCategoryListSpecs.searchSeason(season);*/

        /*String sMiddleCategoryId = Integer.toString(middleCategoryId);
        Specification<ProductCategoryList> middleCategorySpec = ProductCategoryListSpecs.searchMiddleCategoryID(sMiddleCategoryId.charAt(0) - '0');*/
        //Specification<ProductCategoryList> compositeSpec = categorySpec.and(middleCategorySpec).and(priceSpec).and(seasonSpec);
        Specification<ProductCategoryList> compositeSpec = categorySpec.and(middleCategorySpec);



        return iProductCategoryListRepository.findAll(compositeSpec);
    }


}
