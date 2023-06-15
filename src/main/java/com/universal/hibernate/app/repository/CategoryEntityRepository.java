package com.universal.hibernate.app.repository;

import com.universal.hibernate.app.configuration.hibernate.Datasource;
import com.universal.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.universal.hibernate.app.entity.CategoryEntity;

public class CategoryEntityRepository extends HibernateAbstractRepository<CategoryEntity, Long> {
    public CategoryEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
