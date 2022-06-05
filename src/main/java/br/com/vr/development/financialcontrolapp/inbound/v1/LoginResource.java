package br.com.vr.development.financialcontrolapp.inbound.v1;

import br.com.vr.development.financialcontrolapp.inbound.v1.dto.LoginDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/login")
@RestController
@AllArgsConstructor
public class LoginResource {

    @PostMapping
    public ResponseEntity<?> logar(LoginDTO request) {


        return ResponseEntity.ok().build();
    }

}
