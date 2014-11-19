package com.sbhachu.demo.dao.impl;

import com.sbhachu.demo.dao.BaseDAO;
import com.sbhachu.demo.models.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public class UserModelDAO extends BaseDAO<UserModel> {

    public UserModelDAO() {
        super(UserModel.class);
    }

    public UserModel findByUsername(String username) {
        return findByInsensitiveParameter("username", username);
    }

    public int countByUsername(String username) {
        return ((Long) this.sessionFactory.getCurrentSession()
                .createQuery("select count(u) from UserModel as u where u.username = :username")
                .setParameter("username", username).uniqueResult()).intValue();
    }
}
