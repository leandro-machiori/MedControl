package com.leandro.medcontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class CadastroUsuario extends AppCompatActivity {
    private EditText edtNome, edtEmail, edtTelefone, edtSenha, edtConfirmarSenha;
    private Button btnCadastrar;
    private Button btnVoltar;
    private UsuarioController usuarioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioController = new UsuarioController(this);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        aplicarMascaraTelefone();
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNome.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String telefone = edtTelefone.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();
                String confirmarSenha = edtConfirmarSenha.getText().toString().trim();
                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    Toast.makeText(CadastroUsuario.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!emailValido(email)) {
                    Toast.makeText(CadastroUsuario.this, "E-mail inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!senha.equals(confirmarSenha)) {
                    Toast.makeText(CadastroUsuario.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    return;
                }
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setTelefone(telefone);
                usuario.setSenha(senha);
                usuarioController.salvarUsuario(usuario);
                Toast.makeText(CadastroUsuario.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CadastroUsuario.this, login.class));
                finish();
            }
        });
    }
    private boolean emailValido(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void aplicarMascaraTelefone() {
        edtTelefone.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                String str = s.toString().replaceAll("[^\\d]", "");
                StringBuilder telefoneFormatado = new StringBuilder();

                if (str.length() > 0) {
                    telefoneFormatado.append("(");
                    telefoneFormatado.append(str.substring(0, Math.min(2, str.length())));
                }
                if (str.length() >= 3) {
                    telefoneFormatado.append(") ");
                    telefoneFormatado.append(str.substring(2, Math.min(7, str.length())));
                }
                if (str.length() >= 7) {
                    telefoneFormatado.append("-");
                    telefoneFormatado.append(str.substring(7, Math.min(11, str.length())));
                }
                isUpdating = true;
                edtTelefone.setText(telefoneFormatado.toString());
                edtTelefone.setSelection(edtTelefone.getText().length());
                isUpdating = false;
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
        btnVoltar.setOnClickListener(v -> finish());
    }
}