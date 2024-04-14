package com.fmt.educafloripa.service;

import com.fmt.educafloripa.contrller.dto.request.CadastroRequest;
import com.fmt.educafloripa.contrller.dto.request.LoginRequest;
import com.fmt.educafloripa.contrller.dto.responce.CadastroResponse;
import com.fmt.educafloripa.contrller.dto.responce.LoginResponse;

public interface UsuarioService {
    CadastroResponse cadastrarUsuario(CadastroRequest cadastroRequest);
    LoginResponse criarToken(LoginRequest loginRequest);
}
