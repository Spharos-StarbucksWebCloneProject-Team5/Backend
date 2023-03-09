package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.product.vo.RequestProductImageList;
import com.example.Starbucks.product.vo.ResponseProductImageList;

import java.util.List;

public interface IProductImageListService {
    void addProductImage(RequestProductImageList requestProductImageList);


    List<ResponseProductImageList> getByProductId(Long productId);

    List<ResponseProductImageList> getAll() ;
    void updateProductImageList(Long id, RequestProductImageList requestProductImageList);
    void deleteProductImageList(Long id);
}
