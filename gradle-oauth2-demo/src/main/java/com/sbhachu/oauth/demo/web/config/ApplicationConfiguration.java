package com.sbhachu.oauth.demo.web.config;

/**
 * Created by sbhachu on 24/11/2014.
 */
public class ApplicationConfiguration {
    private String root;

    private String host;

    private String displayName;

    private String managerAccountFirstName;

    private String managerAccountLastName;

    private String managerAccountEmail;

    private String managerAccountPassword;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getManagerAccountFirstName() {
        return managerAccountFirstName;
    }

    public void setManagerAccountFirstName(String managerAccountFirstName) {
        this.managerAccountFirstName = managerAccountFirstName;
    }

    public String getManagerAccountLastName() {
        return managerAccountLastName;
    }

    public void setManagerAccountLastName(String managerAccountLastName) {
        this.managerAccountLastName = managerAccountLastName;
    }

    public String getManagerAccountEmail() {
        return managerAccountEmail;
    }

    public void setManagerAccountEmail(String managerAccountEmail) {
        this.managerAccountEmail = managerAccountEmail;
    }

    public String getManagerAccountPassword() {
        return managerAccountPassword;
    }

    public void setManagerAccountPassword(String managerAccountPassword) {
        this.managerAccountPassword = managerAccountPassword;
    }
}

