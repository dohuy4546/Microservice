package com.husony.device_service.repository.impl;


import com.husony.device_service.pojo.Category;
import com.husony.device_service.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getCategory(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        cq.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }
            cq.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Category c) {
        if (c.getId() != null && entityManager.find(Category.class, c.getId()) != null) {
            entityManager.merge(c);
        } else {
            entityManager.persist(c);
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void deleteCategory(Long id) {
        entityManager.remove(this.getCategoryById(id));
    }

}
