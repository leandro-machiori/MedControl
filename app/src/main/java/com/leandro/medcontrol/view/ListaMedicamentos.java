package com.leandro.medcontrol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.leandro.medcontrol.R;
import com.leandro.medcontrol.adapter.MedControlAdapter;
import com.leandro.medcontrol.adapter.MedControlAdapter;
import com.leandro.medcontrol.database.MedicamentoDAO;
import com.leandro.medcontrol.model.Medicamento;
import java.util.List;

public class ListaMedicamentos extends AppCompatActivity {
    private ListView listView;
    private MedicamentoDAO medicamentoDAO;
    private List<Medicamento> lista;
    private MedControlAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.lvMedicamentos);
        medicamentoDAO = new MedicamentoDAO(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicamento m = lista.get(position);
                Intent intent = new Intent(ListaMedicamentos.this, CadastroMedicamentos.class);
                intent.putExtra("medicamento", m); // se m for Serializable
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Medicamento m = lista.get(i);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir medicamento")
                    .setMessage("Tem certeza que deseja excluir este medicamento?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        medicamentoDAO.excluir(m);
                        carregarLista();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
            return true;
        });
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    private void carregarLista() {
        lista = medicamentoDAO.listarMedicamentos();
        adapter = new MedControlAdapter(this, lista);
        listView.setAdapter(adapter);
    }
}
