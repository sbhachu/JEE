package com.sbhachu.oauth.demo.dao.impl;

import com.sbhachu.oauth.demo.dao.BaseDAO;
import com.sbhachu.oauth.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDAO<User> {

    public UserDaoImpl() {
        super(User.class);
    }

    public User findByEmailAddress(String emailAddress) {
        return findByInsensitiveParameter("email", emailAddress);
    }

    public int countByEmailAddress(String emailAddress) {
        return ((Long) this.sessionFactory.getCurrentSession()
                .createQuery("select count(u) from User as u where u.email = :email")
                .setParameter("email", emailAddress)
                .uniqueResult())
                .intValue();
    }
}
