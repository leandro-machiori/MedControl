package com.leandro.medcontrol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedControlDB";
    private static final int DATABASE_VERSION = 1;
    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsuarioTable = "CREATE TABLE usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "email TEXT, " +
                "telefone TEXT, " +
                "senha TEXT)";
        String createMedicamentoTable = "CREATE TABLE medicamento (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "horario TEXT, " +
                "quantidade INTEGER, " +
                "observacoes TEXT)";
        db.execSQL(createUsuarioTable);
        db.execSQL(createMedicamentoTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS medicamento");
        onCreate(db);
    }
}