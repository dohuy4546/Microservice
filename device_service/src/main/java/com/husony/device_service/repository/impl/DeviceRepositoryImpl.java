package com.husony.device_service.repository.impl;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Device;
import com.husony.device_service.repository.DeviceRepository;
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
public class DeviceRepositoryImpl implements DeviceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Device> getDevices(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Device> q = b.createQuery(Device.class);
        Root<Device> root = q.from(Device.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }

            String manuId = params.get("manuId");
            if (manuId != null && !manuId.isEmpty()) {
                Predicate p2 = b.equal(root.get("manufacturerId").get("id"), Long.parseLong(manuId));
                predicates.add(p2);
            }

            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                Predicate p3 = b.equal(root.get("deviceCategoryId").get("id"), Long.parseLong(cateId));
                predicates.add(p3);
            }

            String status = params.get("status");
            if (status != null && !status.isEmpty()) {
                Predicate p4 = b.equal(root.get("status"),status);
                predicates.add(p4);
            }

            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Device d) {
        if (d.getId() != null && entityManager.find(Device.class, d.getId()) != null) {
            entityManager.merge(d);
        } else {
            entityManager.persist(d);
        }
    }

    @Override
    public Device getDeviceById(Long id) {
        return entityManager.find(Device.class, id);
    }

    @Override
    public void deleteDevice(Long id) {
        entityManager.remove(entityManager.find(Device.class, id));
    }


}
