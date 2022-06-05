package br.com.vr.development.financialcontrolapp.inbound.v1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginDTO {

    private String username;
    private String password;

}
