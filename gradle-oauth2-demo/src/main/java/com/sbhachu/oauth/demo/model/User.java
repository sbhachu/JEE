package com.sbhachu.oauth.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbhachu.oauth.demo.model.security.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.security.Principal;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Principal {

    private static final long serialVersionUID = -7563388846851060551L;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private Boolean enabled;

    private Date lastLogin;

    public User() {
    }

    @Override
    public String getName() {
        return email;
    }

    public void setName(String name) {
        //
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", lastLogin=" + lastLogin +
                "} " + super.toString();
    }
}
