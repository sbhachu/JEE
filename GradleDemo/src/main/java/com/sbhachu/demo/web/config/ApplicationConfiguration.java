package com.sbhachu.demo.web.config;

public class ApplicationConfiguration {
    private String root;

    private String host;

    private String displayName;

    private String managerAccountUsername;

    private String managerAccountPassword;

    private String managerAccountEmail;

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

    public String getManagerAccountUsername() {
        return managerAccountUsername;
    }

    public void setManagerAccountUsername(String managerAccountUsername) {
        this.managerAccountUsername = managerAccountUsername;
    }

    public String getManagerAccountPassword() {
        return managerAccountPassword;
    }

    public void setManagerAccountPassword(String managerAccountPassword) {
        this.managerAccountPassword = managerAccountPassword;
    }

    public String getManagerAccountEmail() {
        return managerAccountEmail;
    }

    public void setManagerAccountEmail(String managerAccountEmail) {
        this.managerAccountEmail = managerAccountEmail;
    }
}
