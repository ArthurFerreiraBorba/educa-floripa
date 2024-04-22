package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.CadastroRequest;
import com.fmt.educafloripa.controller.dto.request.LoginRequest;
import com.fmt.educafloripa.controller.dto.response.CadastroResponse;
import com.fmt.educafloripa.controller.dto.response.LoginResponse;
import com.fmt.educafloripa.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

@Slf4j
@RestController
@RequestMapping ("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping ("cadastro")
    public ResponseEntity<CadastroResponse> cadastrarUsuario(@RequestHeader(name = "Authorization") String token, @RequestBody CadastroRequest cadastro) {
        nivelAcesso(token, Arrays.asList(1L));

        log.info("cadastrando usuário");

        return ResponseEntity.status(201).body(service.cadastrarUsuario(cadastro));
    }

    @PostMapping ("login")
    public ResponseEntity<LoginResponse> logar(@RequestBody LoginRequest login) {

        log.info("logando usuário com login {}", login.login());

        return ResponseEntity.status(201).body(service.criarToken(login));
    }
}
