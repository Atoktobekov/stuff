package org.example.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private String firstName;
    private String secondName;
    private String inn;
    private String bankAccount;
}
