package com.ladykoala.dao;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "account")
public class AccountDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;
    @Column
    private String lastname;
    @Column
    private String firstname;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String contactNo;
    @Column
    private boolean verified;
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column
    private float WalletBalance;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private String cordakycid;
}

