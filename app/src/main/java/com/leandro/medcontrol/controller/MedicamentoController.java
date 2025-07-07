package com.leandro.medcontrol.controller;

import android.content.Context;
import com.leandro.medcontrol.database.MedicamentoDAO;
import com.leandro.medcontrol.model.Medicamento;
import java.util.List;

public class MedicamentoController {
    private MedicamentoDAO medicamentoDAO;
    public MedicamentoController(Context context) {
        medicamentoDAO = new MedicamentoDAO(context);
    }
    public void salvarMedicamento(Medicamento medicamento) {
        medicamentoDAO.salvar(medicamento);
    }
    public void atualizarMedicamento(Medicamento medicamento) {
        medicamentoDAO.atualizar(medicamento);
    }
    public void excluirMedicamento(Medicamento medicamento) {
        medicamentoDAO.excluir(medicamento);
    }
    public List<Medicamento> listarMedicamentos() {
        return medicamentoDAO.listarMedicamentos();
    }
}