package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.pojo.MaintenanceType;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.EmployeeRepository;
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
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getEmployee(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Employee> q = b.createQuery(Employee.class);
        Root<Employee> root = q.from(Employee.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("name")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }

            String userId = params.get("userId");
            if (userId != null && !userId.isEmpty()) {
                Predicate p2 = b.equal(root.get("userId"), Long.parseLong(userId));
                predicates.add(p2);
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Employee l) {
        if (l.getId() != null && entityManager.find(Employee.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(Long id) {
        entityManager.remove(this.getEmployeeById(id));
    }
}
