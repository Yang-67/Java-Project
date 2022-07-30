package com.yang.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name",length = 50)
    private String lastName;
    @Column //省略默认列名就是属性名
    private String email;

    public User() {
    }

    public User(Integer id, String lastName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
