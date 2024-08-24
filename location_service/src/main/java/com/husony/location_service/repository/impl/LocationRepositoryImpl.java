package com.husony.location_service.repository.impl;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.repository.LocationRepository;
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
public class LocationRepositoryImpl implements LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Location> getLocation(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Location> q = b.createQuery(Location.class);
        Root<Location> root = q.from(Location.class);
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
    public void addOrUpdate(Location l) {
        if (l.getId() != null && entityManager.find(Location.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Location getLocationById(Long id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    public void deleteLocation(Long id) {
        entityManager.remove(this.getLocationById(id));
    }

}
