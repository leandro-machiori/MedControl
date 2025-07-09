package com.leandro.medcontrol.view;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.leandro.medcontrol.R;
import com.leandro.medcontrol.database.MedicamentoDAO;
import com.leandro.medcontrol.model.Medicamento;

import java.util.Arrays;

public class CadastroMedicamentos extends AppCompatActivity {
    private EditText editNome, editHorario, editQuantidade, editObservacoes;
    private Button btnSalvar, btnVoltar;
    private MedicamentoDAO medicamentoDAO;
    private Medicamento medicamentoEditando = null;
    private final String CHANNEL_ID = "canal_medcontrol";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        // Solicita permissão para notificações no Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
        editNome = findViewById(R.id.edtNomeMedicamento);
        editHorario = findViewById(R.id.edtHorarios);
        editQuantidade = findViewById(R.id.edtQuantidade);
        editObservacoes = findViewById(R.id.edtObservacoes);
        btnSalvar = findViewById(R.id.btnSalvarMedicamento);
        btnVoltar = findViewById(R.id.btnVoltar); // Certifique-se de ter esse botão no XML
        medicamentoDAO = new MedicamentoDAO(this);

        if (getIntent().hasExtra("medicamento")) {
            medicamentoEditando = (Medicamento) getIntent().getSerializableExtra("medicamento");
            preencherCampos(medicamentoEditando);
        }
        btnSalvar.setOnClickListener(v -> {
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
                novo.setHorarios(Arrays.asList(horario.split(",")));
                novo.setQuantidade(quantidade);
                novo.setObservacoes(observacoes);
                medicamentoDAO.salvar(novo);
                mostrarNotificacao(novo.getNome());
                Toast.makeText(this, "Medicamento salvo!", Toast.LENGTH_SHORT).show();
            } else {
                medicamentoEditando.setNome(nome);
                medicamentoEditando.setHorarios(Arrays.asList(horario.split(","))); // CERTO
                medicamentoEditando.setQuantidade(quantidade);
                medicamentoEditando.setObservacoes(observacoes);
                medicamentoDAO.atualizar(medicamentoEditando);
                mostrarNotificacao(medicamentoEditando.getNome());
                Toast.makeText(this, "Medicamento atualizado!", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
        btnVoltar.setOnClickListener(v -> finish());
    }
    private void preencherCampos(Medicamento m) {
        editNome.setText(m.getNome());
        editHorario.setText(String.join(",", m.getHorarios()));
        editQuantidade.setText(String.valueOf(m.getQuantidade()));
        editObservacoes.setText(m.getObservacoes());
    }
    private void mostrarNotificacao(String nomeMedicamento) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.medcontrol3) // Substitua com seu ícone
                .setContentTitle("MedControl")
                .setContentText("Medicamento salvo: " + nomeMedicamento)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify((int) System.currentTimeMillis(), builder.build());
        }
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MedControl Notificações";
            String description = "Canal para notificações de medicamentos";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}