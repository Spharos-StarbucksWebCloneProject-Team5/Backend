package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Category;
import com.example.Starbucks.product.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService{


    private final ICategoryRepository iCategoryRepository;


    @Override
    public void addCategory(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public Category getCategory(Integer categoryId) {
//        Category category = iCategoryRepository.findById(categoryId).get();
//        if(category != null){
//            log.info("not null");
//            return null;
//        }
        return iCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public List<Category> getAllType(String categoryType) {
        return iCategoryRepository.findAllByType(categoryType);
    }
}
