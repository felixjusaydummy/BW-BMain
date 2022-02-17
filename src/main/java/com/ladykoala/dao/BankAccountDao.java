package com.ladykoala.dao;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bank_account")
public class BankAccountDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long userId;
    @Column
    private String bankAccount;
    @Column
    private String bankName;
    @Column
    private String accountNumber;
    @Column
    private String cordakycid;
}
