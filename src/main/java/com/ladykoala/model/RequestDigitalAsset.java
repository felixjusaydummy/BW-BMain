package com.ladykoala.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestDigitalAsset {
    private String username;
    private String currency;
    private String host;
    private String value;
    private boolean validated;
}
