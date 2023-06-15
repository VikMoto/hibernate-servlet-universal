package com.universal.hibernate.app.repository;

import com.universal.hibernate.app.configuration.hibernate.Datasource;
import com.universal.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.universal.hibernate.app.entity.ProductEntity;

public class ProductEntityRepository extends HibernateAbstractRepository<ProductEntity, Long> {
    public ProductEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
