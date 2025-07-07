package com.leandro.medcontrol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.medcontrol.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private SQLiteDatabase db;
    public UsuarioDAO(Context context) {
        AppDatabase dbHelper = new AppDatabase(context);
        db = dbHelper.getWritableDatabase();
    }
    public void salvar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());
        values.put("senha", usuario.getSenha());
        db.insert("usuario", null, values);
    }
    public Usuario buscarUsuario(String nome, String senha) {
        Cursor cursor = db.query("usuario", null, "nome=? AND senha=?",
                new String[]{nome, senha}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            cursor.close();
            return usuario;
        }
        return null;
    }
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = db.query("usuario", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            usuarios.add(usuario);
        }
        cursor.close();
        return usuarios;
    }
}