package com.leandro.medcontrol.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.leandro.medcontrol.R;
import com.leandro.medcontrol.model.Medicamento;
import java.util.List;

public class MedControlAdapter extends BaseAdapter {
    private Context context;
    private List<Medicamento> lista;
    public MedControlAdapter(Context context, List<Medicamento> lista) {
        this.context = context;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }
    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicamento medicamento = lista.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false);
        TextView txtNome = view.findViewById(R.id.tvNomeMedicamento);
        TextView txtHorario = view.findViewById(R.id.tvHorarioMedicamento);
        txtNome.setText(medicamento.getNome());
        txtHorario.setText("Horário: " + medicamento.getHorario());
        return view;
    }
}