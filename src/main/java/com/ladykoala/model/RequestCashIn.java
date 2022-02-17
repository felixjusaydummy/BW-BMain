package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestCashIn {

    private String accountNumber;
    private String backName;
    private String bankAccount;
    private float amount;
}
