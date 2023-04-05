package com.teamSiHyun.Starbucks.api.product;

import com.teamSiHyun.Starbucks.api.category.model.CategoryList;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategoryListSpecs {
    public static Specification<CategoryList> searchCategoryID(Integer categoryId){
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }
    public static Specification<CategoryList> searchMiddleCategoryID(Integer middleCategoryId){

        return (root, query, cb) -> cb.equal(root.get("middleCategory").get("id"), middleCategoryId);

    }
    public static Specification<CategoryList> searchPrice(Integer price){

        return (root, query, cb) -> cb.equal(root.get("product").get("price"), price);

    }
    public static Specification<CategoryList> searchSeason(Integer season){

        return (root, query, cb) -> cb.equal(root.get("product").get("season"), season);

    }
}
