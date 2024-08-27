package com.husony.maintenance_repair_service.repository.impl;

import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.pojo.Report;
import com.husony.maintenance_repair_service.repository.ReportRepository;
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
public class ReportRepositoryImpl implements ReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Report> getReport(Map<String, String> params) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Report> q = b.createQuery(Report.class);
        Root<Report> root = q.from(Report.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String name = params.get("q");
            if (name != null && !name.isEmpty()) {
                Predicate p1 = b.like(b.lower(root.get("description")), "%" + name.toLowerCase() + "%");
                predicates.add(p1);
            }
            try {
                String strDate = params.get("occurrenceDate");
                if (strDate != null && !strDate.isEmpty()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(strDate);
                    Predicate p2 = b.equal(root.get("occurrenceDate"), date);
                    predicates.add(p2);
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            q.where(predicates.toArray(new Predicate[0]));
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Report l) {
        if (l.getId() != null && entityManager.find(Report.class, l.getId()) != null) {
            entityManager.merge(l);
        } else {
            entityManager.persist(l);
        }
    }

    @Override
    public Report getReportById(Long id) {
        return entityManager.find(Report.class, id);
    }

    @Override
    public void deleteReport(Long id) {
        entityManager.remove(this.getReportById(id));
    }
}
