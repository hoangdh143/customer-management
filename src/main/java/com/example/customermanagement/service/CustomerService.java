package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;
import com.github.tennaito.rsql.jpa.JpaCriteriaCountQueryVisitor;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {
    private final EntityManager entityManager;

    public CustomerService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Customer> searchByQuery(String queryString) {
        RSQLVisitor<CriteriaQuery<Customer>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        CriteriaQuery<Customer> query;
        query = getCriteriaQuery(queryString, visitor);
        List<Customer> resultList = entityManager.createQuery(query).getResultList();
        if (resultList == null || resultList.isEmpty()){
            return Collections.emptyList();
        }
        return resultList;
    }

    public Long countByQuery(String queryString) {
        RSQLVisitor<CriteriaQuery<Long>, EntityManager> visitor = new JpaCriteriaCountQueryVisitor<Customer>();
        CriteriaQuery<Long> query;
        query = getCriteriaQuery(queryString, visitor);

        return entityManager.createQuery(query).getSingleResult();
    }

    private <T> CriteriaQuery<T> getCriteriaQuery(String queryString, RSQLVisitor<CriteriaQuery<T>, EntityManager> visitor) {
        Node rootNode;
        CriteriaQuery<T> query;
        try {
            rootNode = new RSQLParser().parse(queryString);
            query = rootNode.accept(visitor, entityManager);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
        return query;
    }
}
