package com.ladykoala.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ResponseCashIn {
    private String account;
    private float amount;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date created;
}
