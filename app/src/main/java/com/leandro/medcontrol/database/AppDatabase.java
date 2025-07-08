package com.leandro.medcontrol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "medcontrol-v2.sqlite";
    private static final int DATABASE_VERSION = 1;
    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela usuarios
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "senha TEXT NOT NULL)");

        // Criação da tabela medicamento
        db.execSQL("CREATE TABLE IF NOT EXISTS medicamento (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "horario TEXT NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "observacoes TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS medicamento");
        onCreate(db);
    }
}