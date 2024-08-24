package com.husony.device_service.repository.impl;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Device;
import com.husony.device_service.pojo.Manufacturer;
import com.husony.device_service.repository.ManufacturerRepository;
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
public class ManufacturerRepositoryImpl implements ManufacturerRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Manufacturer> getManufacturer(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> q = b.createQuery(Manufacturer.class);
        Root<Manufacturer> root = q.from(Manufacturer.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Manufacturer m) {
        if (m.getId() != null && entityManager.find(Manufacturer.class, m.getId()) != null) {
            entityManager.merge(m);
        } else {
            entityManager.persist(m);
        }
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        return entityManager.find(Manufacturer.class, id);
    }

    @Override
    public void deleteManufacturer(Long id) {
        entityManager.remove(this.getManufacturerById(id));
    }


}
