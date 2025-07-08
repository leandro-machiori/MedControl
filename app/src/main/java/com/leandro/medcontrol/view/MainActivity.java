package com.leandro.medcontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.leandro.medcontrol.R;

public class MainActivity extends AppCompatActivity {
    private Button btnListaMedicamentos, btnCadastrarMedicamento, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnListaMedicamentos = findViewById(R.id.btnListaMedicamentos);
        btnCadastrarMedicamento = findViewById(R.id.btnCadastrarMedicamento);
        btnSair = findViewById(R.id.btnSair);

        btnListaMedicamentos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaMedicamentos.class);
            startActivity(intent);
        });
        btnCadastrarMedicamento.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroMedicamentos.class);
            startActivity(intent);
        });
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                // Finaliza a activity atual para não voltar ao clicar no botão "Voltar"
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}