package com.tech.techno.repository;


import com.tech.techno.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TechnoDataRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Customer> getAllCustomerByKeyword(String keyword[]) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ArrayList<Predicate> predicates = new ArrayList<>();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customers = query.from(Customer.class);

        for (String key : keyword) {
            predicates.add(criteriaBuilder.like(customers.get("keywords"), "%" + key + "%"));
        }
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        query.select(customers);
        return entityManager.createQuery(query).getResultList();
    }
}
