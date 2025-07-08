package com.leandro.medcontrol.view;

import static com.leandro.medcontrol.R.*;
import static com.leandro.medcontrol.R.id.btnVoltar;

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
import com.leandro.medcontrol.database.MedicamentoDAO;
import com.leandro.medcontrol.model.Medicamento;

public class CadastroMedicamentos extends AppCompatActivity {
    private EditText editNome, editHorario, editQuantidade, editObservacoes;
    private Button btnSalvar;
    private MedicamentoDAO medicamentoDAO;
    private Button btnVoltar;
    private Medicamento medicamentoEditando = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnVoltar = findViewById(id.btnVoltar);
        editNome = findViewById(R.id.edtNomeMedicamento);
        editHorario = findViewById(R.id.edtHorario);
        editQuantidade = findViewById(R.id.edtQuantidade);
        editObservacoes = findViewById(R.id.edtObservacoes);
        btnSalvar = findViewById(R.id.btnSalvarMedicamento);

        btnVoltar.setOnClickListener(v -> finish());

        medicamentoDAO = new MedicamentoDAO(this);
        if (getIntent().hasExtra("medicamento")) {
            medicamentoEditando = (Medicamento) getIntent().getSerializableExtra("medicamento");
            preencherCampos(medicamentoEditando);
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString().trim();
                String horario = editHorario.getText().toString().trim();
                String quantidadeStr = editQuantidade.getText().toString().trim();
                String observacoes = editObservacoes.getText().toString().trim();

                if (nome.isEmpty() || horario.isEmpty() || quantidadeStr.isEmpty()) {
                    Toast.makeText(CadastroMedicamentos.this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
                    return;
                }
                int quantidade = Integer.parseInt(quantidadeStr);

                if (medicamentoEditando == null) {
                    Medicamento novo = new Medicamento();
                    novo.setNome(nome);
                    novo.setHorario(horario);
                    novo.setQuantidade(quantidade);
                    novo.setObservacoes(observacoes);
                    medicamentoDAO.salvar(novo);
                    Toast.makeText(CadastroMedicamentos.this, "Medicamento salvo!", Toast.LENGTH_SHORT).show();
                } else {
                    medicamentoEditando.setNome(nome);
                    medicamentoEditando.setHorario(horario);
                    medicamentoEditando.setQuantidade(quantidade);
                    medicamentoEditando.setObservacoes(observacoes);
                    medicamentoDAO.atualizar(medicamentoEditando);
                    Toast.makeText(CadastroMedicamentos.this, "Medicamento atualizado!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
    private void preencherCampos(Medicamento m) {
        editNome.setText(m.getNome());
        editHorario.setText(m.getHorario());
        editQuantidade.setText(String.valueOf(m.getQuantidade()));
        editObservacoes.setText(m.getObservacoes());
    }
}