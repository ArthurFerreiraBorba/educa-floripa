package com.fmt.educafloripa.contrller;

import com.fmt.educafloripa.contrller.dto.request.CadastroRequest;
import com.fmt.educafloripa.contrller.dto.request.LoginRequest;
import com.fmt.educafloripa.contrller.dto.responce.CadastroResponse;
import com.fmt.educafloripa.contrller.dto.responce.LoginResponse;
import com.fmt.educafloripa.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping ("cadastro")
    public ResponseEntity<CadastroResponse> cadastrarUsuario(@RequestBody CadastroRequest cadastro) {
        return ResponseEntity.status(201).body(service.cadastrarUsuario(cadastro));
    }

    @PostMapping ("login")
    public ResponseEntity<LoginResponse> logar(@RequestBody LoginRequest login) {
        return ResponseEntity.status(201).body(service.criarToken(login));
    }
}
