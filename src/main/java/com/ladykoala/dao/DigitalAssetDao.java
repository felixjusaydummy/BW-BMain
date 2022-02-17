package com.ladykoala.dao;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "account_digitalasset")
public class DigitalAssetDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long userId;
    @Column
    private String currency;
    @Column
    private String host;
    @Column
    private String value;
    @Column
    private boolean validated;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
