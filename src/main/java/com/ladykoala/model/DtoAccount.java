package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class DtoAccount {

    private long userId;
    private String lastname;
    private String firstname;
    private String address;
    private String email;
    private String contactNo;
    private boolean verified;
    private Date birthday;
    private String userType;
}
