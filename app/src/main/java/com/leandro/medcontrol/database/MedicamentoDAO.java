package com.leandro.medcontrol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.medcontrol.model.Medicamento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicamentoDAO {
    private SQLiteDatabase db;
    public MedicamentoDAO(Context context) {
        AppDatabase dbHelper = new AppDatabase(context);
        db = dbHelper.getWritableDatabase();
    }
    public void salvar(Medicamento medicamento) {
        ContentValues values = new ContentValues();
        values.put("nome", medicamento.getNome());
        values.put("horarios", String.join(",", medicamento.getHorarios()));
        values.put("quantidade", medicamento.getQuantidade());
        values.put("observacoes", medicamento.getObservacoes());
        db.insert("medicamento", null, values);
    }
    public void atualizar(Medicamento medicamento) {
        ContentValues values = new ContentValues();
        values.put("nome", medicamento.getNome());
        values.put("horarios", String.join(",", medicamento.getHorarios()));
        values.put("quantidade", medicamento.getQuantidade());
        values.put("observacoes", medicamento.getObservacoes());
        db.update("medicamento", values, "id=?", new String[]{String.valueOf(medicamento.getId())});
    }
    public void excluir(Medicamento medicamento) {
        db.delete("medicamento", "id=?", new String[]{String.valueOf(medicamento.getId())});
    }
    public List<Medicamento> listarMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        Cursor cursor = db.query("medicamento", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Medicamento medicamento = new Medicamento();
            medicamento.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            medicamento.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            String horariosStr = cursor.getString(cursor.getColumnIndexOrThrow("horarios"));
            medicamento.setHorarios(Arrays.asList(horariosStr.split(",")));
            medicamento.setQuantidade(cursor.getInt(cursor.getColumnIndexOrThrow("quantidade")));
            medicamento.setObservacoes(cursor.getString(cursor.getColumnIndexOrThrow("observacoes")));
            medicamentos.add(medicamento);
        }
        cursor.close();
        return medicamentos;
    }
}