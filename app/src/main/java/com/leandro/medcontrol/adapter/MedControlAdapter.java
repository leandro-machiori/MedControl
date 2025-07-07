package com.leandro.medcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import com.leandro.medcontrol.R;
import com.leandro.medcontrol.model.Medicamento;
import java.util.List;

public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {
    private Context context;
    private List<Medicamento> medicamentos;
    public MedicamentoAdapter(Context context, List<Medicamento> medicamentos) {
        super(context, 0, medicamentos);
        this.context = context;
        this.medicamentos = medicamentos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicamento medicamento = medicamentos.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false);
        }
        TextView tvNome = convertView.findViewById(R.id.tvNomeMedicamento);
        TextView tvHorario = convertView.findViewById(R.id.tvHorarioMedicamento);
        TextView tvQuantidade = convertView.findViewById(R.id.tvQuantidadeMedicamento);
        tvNome.setText(medicamento.getNome());
        tvHorario.setText("Horário: " + medicamento.getHorario());
        tvQuantidade.setText("Quantidade: " + medicamento.getQuantidade());
        return convertView;
    }
}