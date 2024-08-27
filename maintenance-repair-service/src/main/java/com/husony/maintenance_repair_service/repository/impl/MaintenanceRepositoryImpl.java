package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.pojo.Maintenance;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.MaintenanceRepository;
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
public class MaintenanceRepositoryImpl implements MaintenanceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Maintenance> getMaintenance(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Maintenance> q = b.createQuery(Maintenance.class);
        Root<Maintenance> root = q.from(Maintenance.class);
        q.select(root);

        if (params != null) {
            try {
                List<Predicate> predicates = new ArrayList<>();
                String nextMaintenanceDate = params.get("nextMaintenanceDate");

                if (nextMaintenanceDate != null && !nextMaintenanceDate.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date next_maintenance_date = formatter.parse(nextMaintenanceDate);
                    Predicate p1 = b.equal(root.get("nextMaintenanceDate").as(Date.class), next_maintenance_date);
                    predicates.add(p1);
                }

                String maintenanceTypeId = params.get("maintenanceTypeId");
                if (maintenanceTypeId != null && !maintenanceTypeId.isEmpty()) {
                    Predicate p2 = b.equal(root.get("maintenanceTypeId").get("id"), Long.parseLong(maintenanceTypeId));
                    predicates.add(p2);
                }

                String kw = params.get("q");
                if (kw != null && !kw.isEmpty()) {
                    Predicate p3 = b.like(root.get("name"), String.format("%%%s%%", kw));
                    predicates.add(p3);
                }

                q.where(predicates.toArray(Predicate[]::new));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Maintenance l) {
        if (l.getId() != null && entityManager.find(Maintenance.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return entityManager.find(Maintenance.class, id);
    }

    @Override
    public void deleteMaintenance(Long id) {
        entityManager.remove(this.getMaintenanceById(id));
    }
}
