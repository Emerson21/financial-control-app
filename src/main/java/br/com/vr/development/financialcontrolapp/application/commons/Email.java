package br.com.vr.development.financialcontrolapp.application.commons;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Email implements Serializable {

    private String email;
    
}
