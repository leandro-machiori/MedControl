package com.leandro.medcontrol.model;

public class Medicamento {
    private int id;
    private String nome;
    private String horario;
    private int quantidade;
    private String observacoes;
    public Medicamento() {}
    public Medicamento(int id, String nome, String horario, int quantidade, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }
    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}