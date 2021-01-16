package com.vkannuri.security.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** @author Venu Kannuri . */
@Entity
@Table(name = "USER")
public class User {
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PASSWORD")
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
