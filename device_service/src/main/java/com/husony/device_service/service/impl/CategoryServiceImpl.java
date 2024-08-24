package com.husony.device_service.service.impl;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.repository.CategoryRepository;
import com.husony.device_service.service.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getCategory(Map<String, String> params) {
        return this.categoryRepository.getCategory(params);
    }

    @Override
    public void addOrUpdate(Category c) {
        this.categoryRepository.addOrUpdate(c);
    }

    @Override
    public Category getCateById(long id) {
        return this.categoryRepository.getCategoryById(id);
    }

    @Override
    public void deleteCate(long id) {
        this.categoryRepository.deleteCategory(id);
    }
}
