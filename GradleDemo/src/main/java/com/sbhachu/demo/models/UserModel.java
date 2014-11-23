package com.sbhachu.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.security.Principal;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserModel extends BaseModel implements Principal {

    private static final long serialVersionUID = -7563388846851060551L;

    public static final int ROLE_USER = 0;

    public static final int ROLE_ADMINISTRATOR = 1;

    public static final int ROLE_MANAGER = 2;

    private String username;

    private String email;

    private String password;

    private Integer role;

    private Boolean enabled;

    private Date lastLogin;

    public UserModel() {
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        //
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Column(name = "isEnabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", lastLogin=" + lastLogin +
                "} " + super.toString();
    }
}
