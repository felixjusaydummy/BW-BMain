package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@NoArgsConstructor
@Getter
@Setter
public class DtoBankAccount {
    private long userId;
    private String bankAccount;
    private String bankName;
    private String accountNumber;
}
