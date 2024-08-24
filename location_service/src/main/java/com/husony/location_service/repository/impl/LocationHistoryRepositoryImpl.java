package com.husony.location_service.repository.impl;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.pojo.LocationHistory;
import com.husony.location_service.repository.LocationHistoryRepository;
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
public class LocationHistoryRepositoryImpl implements LocationHistoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LocationHistory> getLocationHistory(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<LocationHistory> q = b.createQuery(LocationHistory.class);
        Root<LocationHistory> root = q.from(LocationHistory.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String locationId = params.get("locationId");
            if (locationId != null && !locationId.isEmpty()) {
                Predicate p1 = b.equal(root.get("locationId").get("id"), Long.parseLong(locationId));
                predicates.add(p1);
            }

            String deviceId = params.get("deviceId");
            if (deviceId != null && !deviceId.isEmpty()) {
                Predicate p2 = b.equal(root.get("device"), Long.parseLong(deviceId));
                predicates.add(p2);
            }
            q.where(predicates.toArray(new Predicate[0]));

            String active = params.get("active");
            if (active != null && !active.isEmpty()) {
                if (active == "true"){
                    Predicate p3 = b.equal(root.get("active"), true);
                    predicates.add(p3);
                }else if (active == "false") {
                    Predicate p3 = b.equal(root.get("active"), false);
                    predicates.add(p3);
                }
            }
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(LocationHistory l) {
        if (l.getId() != null && entityManager.find(LocationHistory.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public LocationHistory getLocationHistoryById(Long id) {
        return entityManager.find(LocationHistory.class, id);
    }

    @Override
    public void deleteLocationHistory(Long id) {
        entityManager.remove(this.getLocationHistoryById(id));
    }

}
