package org.example.db.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private String firstName;
    private String secondName;
    private LocalDate dateOfBirth;
    private String inn;
    private String phoneNumber;
    private String bankAccount;
    private BigDecimal balance;
    private String bankName;  // новое поле для банка
}
