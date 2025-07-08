package com.leandro.medcontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leandro.medcontrol.R;
import com.leandro.medcontrol.controller.UsuarioController;
import com.leandro.medcontrol.model.Usuario;

public class login extends AppCompatActivity {
    private EditText editUsuario, editSenha;
    private Button btnLogin, btnCadastrar;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textEsqueciSenha = findViewById(R.id.textEsqueciSenha);
        textEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, RecuperarSenha.class);
                startActivity(intent);
            }
        });
        usuarioController = new UsuarioController(this);
        editUsuario = findViewById(R.id.editUsuario);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editUsuario.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                if (nome.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(login.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Usuario usuario = usuarioController.buscarUsuarioPorNomeESenha(nome, senha);
                if (usuario != null) {
                    Toast.makeText(login.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, CadastroUsuario.class);
                startActivity(intent);
            }
        });
    }
}