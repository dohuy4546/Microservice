package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.Repair;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.RepairRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class RepairRepositoryImpl implements RepairRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Repair> getRepair(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Repair> q = b.createQuery(Repair.class);
        Root<Repair> root = q.from(Repair.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }
            try {
                String strDate = params.get("date");
                if (strDate != null && !strDate.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(strDate);
                    Predicate p2 = b.equal(root.get("date"), date);
                    predicates.add(p2);
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            String deviceId = params.get("deviceId");
            if (deviceId != null && !deviceId.isEmpty()) {
                Predicate p3 = b.equal(root.get("reportId").get("deviceId"), Long.parseLong(deviceId));
                predicates.add(p3);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Repair l) {
        if (l.getId() != null && entityManager.find(Repair.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Repair getRepairById(Long id) {
        return entityManager.find(Repair.class, id);
    }

    @Override
    public void deleteRepair(Long id) {
        entityManager.remove(this.getRepairById(id));
    }
}
