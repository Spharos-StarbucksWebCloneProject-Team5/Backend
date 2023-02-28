package com.example.Starbucks.product;

import com.example.Starbucks.product.model.ProductCategoryList;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategoryListSpecs {
    public static Specification<ProductCategoryList> searchCategoryID(Integer categoryId){
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }
    public static Specification<ProductCategoryList> searchMiddleCategoryID(Integer middleCategoryId){

        return (root, query, cb) -> cb.equal(root.get("middleCategory").get("id"), middleCategoryId);

    }
    public static Specification<ProductCategoryList> searchPrice(Integer price){

        return (root, query, cb) -> cb.equal(root.get("product").get("price"), price);

    }
    public static Specification<ProductCategoryList> searchSeason(Integer season){

        return (root, query, cb) -> cb.equal(root.get("product").get("season"), season);

    }
}
