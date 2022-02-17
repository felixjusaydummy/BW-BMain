package com.ladykoala.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
public class RequestUserDto {
    private String username;
    private String password;
    private String lastname;
    private String firstname;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    private String address;
    private String email;
    private String contactNo;

}

// trans-union