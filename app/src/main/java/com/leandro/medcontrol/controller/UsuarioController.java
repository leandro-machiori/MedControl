package com.leandro.medcontrol.controller;

import android.content.Context;
import com.leandro.medcontrol.database.UsuarioDAO;
import com.leandro.medcontrol.model.Usuario;
public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(Context context) {
        usuarioDAO = new UsuarioDAO(context);
    }
    public void salvarUsuario(Usuario usuario) {
        usuarioDAO.inserir(usuario);
    }
    public Usuario buscarUsuarioPorNomeESenha(String nome, String senha) {
        return usuarioDAO.buscarPorNomeESenha(nome, senha);
    }
    public void alterarSenha(int id, String novaSenha) {
        usuarioDAO.alterarSenha(id, novaSenha);
    }
    public Usuario buscarPorNomeEEmail(String nome, String email) {
        return usuarioDAO.buscarPorNomeEEmail(nome, email);
    }
}