package org.example.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDto {
    private String name;
    private String bic;
    private String address;
}
