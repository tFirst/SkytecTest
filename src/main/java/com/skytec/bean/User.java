package com.skytec.bean;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User { id = " + id + ", login = " + login + ", password = " + password + "}";
    }
}
