package com.leandro.medcontrol.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.leandro.medcontrol.R;
import java.util.ArrayList;
import java.util.List;

public class ListaMedicamentos extends AppCompatActivity {
    private ListView lvMedicamentos;
    private List<String> listaMedicamentos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvMedicamentos = findViewById(R.id.lvMedicamentos);
        // Simulando dados (depois conectar com banco)
        listaMedicamentos.add("Dipirona - 08:00");
        listaMedicamentos.add("Paracetamol - 12:00");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMedicamentos);
        lvMedicamentos.setAdapter(adapter);
        lvMedicamentos.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Clique para editar: " + listaMedicamentos.get(position), Toast.LENGTH_SHORT).show();
            // Aqui você pode abrir tela de edição
        });
        lvMedicamentos.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Clique longo para excluir: " + listaMedicamentos.get(position), Toast.LENGTH_SHORT).show();
            // Aqui você pode excluir o item do banco e atualizar a lista
            return true;
        });
    }
}