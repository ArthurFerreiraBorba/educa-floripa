package com.fmt.educafloripa.service;

import com.fmt.educafloripa.controller.dto.request.CadastroRequest;
import com.fmt.educafloripa.controller.dto.request.LoginRequest;
import com.fmt.educafloripa.controller.dto.response.CadastroResponse;
import com.fmt.educafloripa.controller.dto.response.LoginResponse;

public interface UsuarioService {
    CadastroResponse cadastrarUsuario(CadastroRequest cadastroRequest);
    LoginResponse criarToken(LoginRequest loginRequest);
}
