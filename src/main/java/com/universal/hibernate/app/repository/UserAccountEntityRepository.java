package com.universal.hibernate.app.repository;

import com.universal.hibernate.app.configuration.hibernate.Datasource;
import com.universal.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.universal.hibernate.app.entity.CustomerEntity;
import com.universal.hibernate.app.entity.UserAccountEntity;

public class UserAccountEntityRepository extends HibernateAbstractRepository<UserAccountEntity, CustomerEntity> {
    public UserAccountEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
