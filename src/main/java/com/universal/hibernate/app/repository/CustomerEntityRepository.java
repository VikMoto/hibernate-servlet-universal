package com.universal.hibernate.app.repository;

import com.universal.hibernate.app.configuration.hibernate.Datasource;
import com.universal.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.universal.hibernate.app.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerEntityRepository extends HibernateAbstractRepository<CustomerEntity, Long> {

    public CustomerEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
