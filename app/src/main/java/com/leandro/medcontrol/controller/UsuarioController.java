package com.leandro.medcontrol.controller;

import android.content.Context;

import com.leandro.medcontrol.database.UsuarioDAO;
import com.leandro.medcontrol.model.Usuario;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(Context context) {
        usuarioDAO = new UsuarioDAO(context);
    }
    public void salvarUsuario(Usuario usuario) {
        usuarioDAO.salvar(usuario);
    }
    public Usuario buscarUsuario(String nome, String senha) {
        return usuarioDAO.buscarUsuario(nome, senha);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
}
