package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestBankTransferFund {
    private String account;
    private float amount;
}
