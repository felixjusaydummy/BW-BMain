package com.ladykoala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestApplyLoan {

    private long walletAccountId;
    private String purpose;
    private String amount;
    private String paymentTerms;
    private String occupattion;
    private String grossIncome;
    private String kycId;

    private String bankName;
}
