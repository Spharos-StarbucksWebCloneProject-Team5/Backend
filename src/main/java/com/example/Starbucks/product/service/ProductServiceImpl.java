package com.example.Starbucks.product.service;

import com.example.Starbucks.enums.PageNum;
import com.example.Starbucks.product.dto.ResponseProductList;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ResponseProductList> getAllProduct(int pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum, PageNum.PAGE_SIZE.getValue());
        return iProductRepository.getAllProduct(pageable).stream()
                .map(element -> ResponseProductList.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .price(element.getPrice())
                        .thumbnail(element.getThumbnail())
                        .build()).collect(Collectors.toList());
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
