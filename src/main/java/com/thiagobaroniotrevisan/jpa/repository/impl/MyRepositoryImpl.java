package com.thiagobaroniotrevisan.jpa.repository.impl;

import com.thiagobaroniotrevisan.jpa.repository.MyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MyRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

    @Override
    public List<T> findAll(Specification<T> specification, int offset, int amount) {
        return this.findAll(specification, offset, amount, Sort.unsorted());
    }

    @Override
    public List<T> findAll(Specification<T> specification, int offset, int amount, Sort sort) {
        TypedQuery<T> query = this.getQuery(specification, sort);

        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be less than zero!");
        }

        if (amount < 1) {
            throw new IllegalArgumentException("Amount must not be less than one!");
        }

        query.setFirstResult(offset);
        query.setMaxResults(amount);

        return query.getResultList();
    }
}
