package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.MiddleCategory;
import com.example.Starbucks.product.repository.IMiddleCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class MiddleCategoryServiceImpl implements IMiddleCategoryService{
    private final IMiddleCategoryRepository iMiddleCategoryRepository;

    @Override
    public void addMiddleCategory(MiddleCategory middleCategory) {

        iMiddleCategoryRepository.save(middleCategory);
    }

    @Override
    public MiddleCategory getMiddleCategory(Integer middleCategoryId) {

        return iMiddleCategoryRepository.findById(middleCategoryId).get();
    }

    @Override
    public List<MiddleCategory> getAll() {
        return iMiddleCategoryRepository.findAll();
    }

   /* @Override
    public List<MiddleCategory> getAllCategory(String category) {
        return iMiddleCategoryRepository.findAllByCategory(category);
    }*/
   @Override
   public void updateMiddleCategory(MiddleCategory middleCategory){

       MiddleCategory middleCategory1 = iMiddleCategoryRepository.findById(middleCategory.getId()).get();
       middleCategory1.setName(middleCategory.getName());



       iMiddleCategoryRepository.save(middleCategory1);
   }
}
