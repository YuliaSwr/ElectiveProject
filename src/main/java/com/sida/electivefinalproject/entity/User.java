package com.sida.electivefinalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;
import java.sql.Blob;

@Setter
@Getter
public class User extends BaseEntity implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private UserType userType;
    private AccountStatus accountStatus;
    private String photo;

    public User(int id,
                String firstName,
                String lastName,
                String gender,
                String email,
                String password,
                UserType userType,
                AccountStatus accountStatus,
                String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.accountStatus = accountStatus;
        this.photo = photo;
    }

    public User(String firstName,
                String lastName,
                String gender,
                String email,
                String password,
                UserType userType,
                AccountStatus accountStatus,
                String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.accountStatus = accountStatus;
        this.photo = photo;
    }

}
