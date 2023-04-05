package com.example.Starbucks.api.product.service;

import com.example.Starbucks.api.product.vo.RequestProductImageList;
import com.example.Starbucks.api.product.vo.ResponseProductImageList;

import java.util.List;

public interface IProductImageListService {
    void addProductImage(RequestProductImageList requestProductImageList);


    List<ResponseProductImageList> getByProductId(Long productId);

    List<ResponseProductImageList> getAll() ;
    void updateProductImageList(Long id, RequestProductImageList requestProductImageList);
    void deleteProductImageList(Long id);
}
