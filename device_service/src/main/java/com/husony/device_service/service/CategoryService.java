package com.husony.device_service.service;

import com.husony.device_service.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getCategory(Map<String, String> params);
    void addOrUpdate(Category c);
    Category getCateById(long id);
    void deleteCate(long id);
}
