package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Category;
import com.example.Starbucks.product.repository.ICategoryRepository;
import com.example.Starbucks.product.vo.RequestCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService{


    private final ICategoryRepository iCategoryRepository;


    @Override
    public void addCategory(RequestCategory requestCategory) {
        ModelMapper modelMapper = new ModelMapper();
        Category category = modelMapper.map(requestCategory,Category.class);
        iCategoryRepository.save(category);
    }

    @Override
    public Category getCategory(Integer categoryId) {
        return iCategoryRepository.findById(categoryId).get();
    }

    @Override
    public List<Category> getAll() {
        return iCategoryRepository.findAll();
    }

    /*@Override
    public List<CategoryTypeDto> getAllType(String categoryType) {
        ModelMapper modelMapper = new ModelMapper();
        List<Category> categories = iCategoryRepository.findAllByType(categoryType);
        List<CategoryTypeDto> categoryTypeDtoList = new ArrayList<>();

        categories.forEach(category -> {
            categoryTypeDtoList.add (modelMapper.map(category,CategoryTypeDto.class));

        });
        return null;
    }*/
    @Override
    public void updateCategory(Category category){

        Category category1 = iCategoryRepository.findById(category.getId()).get();
        category1.setName(category.getName());

        iCategoryRepository.save(category1);
    }

    /*@Override
    public List<String> getCategoryTypeName() {
        return iCategoryRepository.groupByType();
    }*/


}