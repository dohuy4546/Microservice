package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.pojo.Maintenance;
import com.husony.maintenance_repair_service.pojo.MaintenanceDevice;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.MaintenanceDeviceRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MaintenanceDeviceRepositoryImpl implements MaintenanceDeviceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MaintenanceDevice> getMaintenanceDevice(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MaintenanceDevice> q = b.createQuery(MaintenanceDevice.class);
        Root<MaintenanceDevice> root = q.from(MaintenanceDevice.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String maintenanceId = params.get("maintenanceId");
            if (maintenanceId != null && !maintenanceId.isEmpty()) {
                Predicate p1 = b.equal(root.get("maintenanceId"), Long.parseLong(maintenanceId));
                predicates.add(p1);
            }

            String userId = params.get("deviceId");
            if (userId != null && !userId.isEmpty()) {
                Predicate p2 = b.equal(root.get("deviceId"), Long.parseLong(userId));
                predicates.add(p2);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(MaintenanceDevice l) {
        if (l.getId() != null && entityManager.find(MaintenanceDevice.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public MaintenanceDevice getMaintenanceDeviceById(Long id) {
        return entityManager.find(MaintenanceDevice.class, id);
    }

    @Override
    public void deleteMaintenanceDevice(Long id) {
        entityManager.remove(this.getMaintenanceDeviceById(id));
    }
}
