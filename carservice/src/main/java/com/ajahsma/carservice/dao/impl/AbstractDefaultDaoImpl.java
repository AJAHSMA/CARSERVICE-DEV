package com.ajahsma.carservice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class AbstractDefaultDaoImpl extends HibernateDaoSupport
{
    @Autowired
    public void defaultSessionFactory(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
        if(getHibernateTemplate() != null) {
            getHibernateTemplate().setCheckWriteOperations(false);
        }
    }
}