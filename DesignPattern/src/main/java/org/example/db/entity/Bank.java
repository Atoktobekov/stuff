package org.example.db.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank {
    private String name;
    private String bic;
    private String address;
    private BigDecimal capital;
}
