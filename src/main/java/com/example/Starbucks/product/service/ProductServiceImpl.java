package com.example.Starbucks.product.service;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository iProductRepository;

    @Override
    public void addProduct(RequestProduct requestProduct) {

        Product product = Product.builder()
                .name(requestProduct.getName())
                .price(requestProduct.getPrice())
                .description(requestProduct.getDescription())
                .thumbnail(requestProduct.getThumbnail())
                .count(requestProduct.getCount())
                .build();
        iProductRepository.save(product);
    }

    @Override
    public ResponseProduct getProduct(Long productId) {
        Product product = iProductRepository.findById(productId).get();
        ResponseProduct responseProduct = ResponseProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .count(product.getCount())
                .build();
        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getAllProduct() {
        List<Product> productList = iProductRepository.findAll();
        List<ResponseProduct> responseProductList =new ArrayList<>();
        for(int i=0;i<productList.size();i++){
            responseProductList.add(ResponseProduct.builder()
                    .id(productList.get(i).getId())
                    .name(productList.get(i).getName())
                    .price(productList.get(i).getPrice())
                    .description(productList.get(i).getDescription())
                    .thumbnail(productList.get(i).getThumbnail())
                    .count(productList.get(i).getCount())
                    .build()
            );
        }
        return responseProductList;
    }

    @Override
    public void updateProduct(Long id, RequestProduct requestProduct){
        Product product = Product.builder()
                .id(id)
                .name(requestProduct.getName())
                .price(requestProduct.getPrice())
                .description(requestProduct.getDescription())
                .thumbnail(requestProduct.getThumbnail())
                .count(requestProduct.getCount())
                .build();


        iProductRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        Product product = iProductRepository.findById(id).get();
        iProductRepository.delete(product);
    }
}
