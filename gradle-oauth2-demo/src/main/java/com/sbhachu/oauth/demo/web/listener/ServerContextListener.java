package com.sbhachu.oauth.demo.web.listener;

/**
 * Created by sbhachu on 24/11/2014.
 */

import com.sbhachu.oauth.demo.dao.impl.UserDaoImpl;
import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.model.security.Role;
import com.sbhachu.oauth.demo.util.MD5EncoderUtil;
import com.sbhachu.oauth.demo.web.config.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

public class ServerContextListener implements ServletContextListener {
    private final Logger logger = Logger.getLogger(ServerContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        ApplicationConfiguration config = (ApplicationConfiguration) webCtx.getBean("applicationConfiguration");
        config.setRoot(webCtx.getServletContext().getRealPath(""));

        logger.info("Server Context Initialized.");

        initializeTables(webCtx);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        @SuppressWarnings("unused")
        ApplicationConfiguration config = (ApplicationConfiguration) webCtx.getBean("applicationConfiguration");

        logger.info("Server Context Destroyed.");
    }

    private void initializeTables(WebApplicationContext webCtx) {
        SessionFactory sessionFactory = (SessionFactory) webCtx.getBean("sessionFactory");
        Session session = sessionFactory.openSession();

        if (TransactionSynchronizationManager.getResource(sessionFactory) == null) {
            TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
        }

        try {
            ApplicationConfiguration config = (ApplicationConfiguration) webCtx.getBean("applicationConfiguration");

            initializeManagerAccount(config, webCtx);

            session.flush();
        } catch (Exception e) {
            logger.error("Error while creating default tables", e);
        } finally {
            session.flush();
            TransactionSynchronizationManager.unbindResource(sessionFactory);
            SessionFactoryUtils.closeSession(session);
        }
    }

    private void initializeManagerAccount(ApplicationConfiguration config, WebApplicationContext webCtx) {
        final UserDaoImpl dao = (UserDaoImpl) webCtx.getBean(UserDaoImpl.class);

        final int manCount = dao.countByEmailAddress(config.getManagerAccountEmail());

        User manager;

        if (manCount == 0) {
            manager = new User();
            manager.setFirstName(config.getManagerAccountFirstName());
            manager.setLastName(config.getManagerAccountLastName());
            manager.setPassword(MD5EncoderUtil.encode(config.getManagerAccountPassword()));
            manager.setRole(Role.ROLE_ADMIN);
            manager.setEmail(config.getManagerAccountEmail());
            manager.setDateCreated(new Date());
            manager.setDateModified(new Date());
            manager.setLastLogin(new Date());
            manager.setEnabled(true);

            dao.create(manager);

            logger.info("Created Manager Account");
        } else {
            manager = dao.findByEmailAddress(config.getManagerAccountEmail());
        }
    }
}

