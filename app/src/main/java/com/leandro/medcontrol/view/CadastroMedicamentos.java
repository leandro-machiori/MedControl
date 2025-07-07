package com.leandro.medcontrol.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leandro.medcontrol.R;

public class CadastroMedicamentos extends AppCompatActivity {
    private EditText edtNomeMedicamento, edtHorario, edtQuantidade, edtObservacoes;
    private Button btnSalvarMedicamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicamento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNomeMedicamento = findViewById(R.id.edtNomeMedicamento);
        edtHorario = findViewById(R.id.edtHorario);
        edtQuantidade = findViewById(R.id.edtQuantidade);
        edtObservacoes = findViewById(R.id.edtObservacoes);
        btnSalvarMedicamento = findViewById(R.id.btnSalvarMedicamento);
        btnSalvarMedicamento.setOnClickListener(v -> {
            // Aqui você pode salvar no banco
            Toast.makeText(this, "Medicamento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}