package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseLinkBank {
    private String account;
    private float balance;
    private String bankName;
    private String bankAccount;
    private String cordaFlowId;
}
