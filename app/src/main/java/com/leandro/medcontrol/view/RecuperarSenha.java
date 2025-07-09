package com.leandro.medcontrol.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leandro.medcontrol.R;
import com.leandro.medcontrol.controller.UsuarioController;
import com.leandro.medcontrol.model.Usuario;

public class RecuperarSenha extends AppCompatActivity {
    private EditText editNome, editEmail, editNovaSenha;
    private Button btnRecuperar;
    private Button btnVoltar;
    private UsuarioController usuarioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_senha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioController = new UsuarioController(this);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editNovaSenha = findViewById(R.id.editNovaSenha);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String novaSenha = editNovaSenha.getText().toString().trim();
                if (nome.isEmpty() || email.isEmpty() || novaSenha.isEmpty()) {
                    Toast.makeText(RecuperarSenha.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Usuario usuario = usuarioController.buscarPorNomeEEmail(nome, email);
                if (usuario != null) {
                    usuarioController.alterarSenha(usuario.getId(), novaSenha);
                    Toast.makeText(RecuperarSenha.this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
                    finish(); // Volta para o login
                } else {
                    Toast.makeText(RecuperarSenha.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVoltar.setOnClickListener(v -> finish());
    }
}