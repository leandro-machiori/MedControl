package com.leandro.medcontrol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.medcontrol.model.Usuario;

public class UsuarioDAO {
    private SQLiteDatabase db;
    public UsuarioDAO(Context context) {
        AppDatabase dbHelper = new AppDatabase(context);
        db = dbHelper.getWritableDatabase();
    }
    public void inserir(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());
        values.put("senha", usuario.getSenha());
        db.insert("usuarios", null, values);
    }
    public Usuario buscarPorNomeESenha(String nome, String senha) {
        Cursor cursor = db.query("usuarios",
                null,
                "nome = ? AND senha = ?",
                new String[]{nome, senha},
                null, null, null);
        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }
    public Usuario buscarPorNomeEEmail(String nome, String email) {
        Cursor cursor = db.query("usuarios",
                null,
                "nome = ? AND email = ?",
                new String[]{nome, email},
                null, null, null);
        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }
    public void alterarSenha(int id, String novaSenha) {
        ContentValues values = new ContentValues();
        values.put("senha", novaSenha);
        db.update("usuarios", values, "id=?", new String[]{String.valueOf(id)});
    }
}