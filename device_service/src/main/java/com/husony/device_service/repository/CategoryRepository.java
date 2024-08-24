package com.husony.device_service.repository;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface CategoryRepository  {
    List<Category> getCategory(Map<String, String> params);
    void addOrUpdate(Category c);
    Category getCategoryById(Long id);
    void deleteCategory(Long id);
}
