package com.teamSiHyun.Starbucks.api.product.repository;

import com.teamSiHyun.Starbucks.api.product.model.ProductImageList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductImageListRepository extends JpaRepository<ProductImageList, Long> {

    List<ProductImageList>findAllByProductId(Long productId);

}
