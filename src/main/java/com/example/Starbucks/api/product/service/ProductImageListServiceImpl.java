package com.example.Starbucks.api.product.service;


import com.example.Starbucks.api.product.model.ProductImageList;
import com.example.Starbucks.api.product.vo.RequestProductImageList;
import com.example.Starbucks.api.product.vo.ResponseProductImageList;
import com.example.Starbucks.api.product.repository.IProductImageListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductImageListServiceImpl implements IProductImageListService{

    private final IProductImageListRepository iProductImageListRepository;
    @Override
    public void addProductImage(RequestProductImageList requestProductImageList) {
        ModelMapper modelMapper = new ModelMapper();
        ProductImageList productImageList = modelMapper.map(requestProductImageList, ProductImageList.class);
        iProductImageListRepository.save(productImageList);
    }

    @Override
    public List<ResponseProductImageList> getByProductId(Long productId) {
        List<ProductImageList> productImageLists = iProductImageListRepository.findAllByProductId(productId);
        List<ResponseProductImageList> responseProductImageLists = new ArrayList<>();
        for(int i=0;i<productImageLists.size();i++){
            responseProductImageLists.add(ResponseProductImageList.builder()
                    .id(productImageLists.get(i).getId())
                    .productId(productImageLists.get(i).getProduct().getId())
                    .image(productImageLists.get(i).getImage())
                    .build()
            );
        }
        return responseProductImageLists;
    }

    @Override
    public List<ResponseProductImageList> getAll() {
        List<ProductImageList> productImageLists = iProductImageListRepository.findAll();
        List<ResponseProductImageList> responseProductImageLists = new ArrayList<>();
        for (ProductImageList productImageList : productImageLists) {
            responseProductImageLists.add(ResponseProductImageList.builder()
                    .id(productImageList.getId())
                    .productId(productImageList.getProduct().getId())
                    .image(productImageList.getImage())
                    .build()
            );
        }
        return responseProductImageLists;
    }


    @Override
    public void updateProductImageList(Long id, RequestProductImageList requestProductImageList){
        //ProductImageList productImageList = iProductImageListRepository.findById(id).get();
        ProductImageList productImageList = ProductImageList.builder()
                .id(id)
                .image(requestProductImageList.getImage())
                .build();


        iProductImageListRepository.save(productImageList);
    }
    @Override
    public void deleteProductImageList(Long id) {
        ProductImageList productImageList = iProductImageListRepository.findById(id).get();
        iProductImageListRepository.delete(productImageList);
    }
}
