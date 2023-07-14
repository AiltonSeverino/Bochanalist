package com.example.obochanalist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {

    private static final String Nome_banco = "banco.db";
    private static final int version = 4;

    public CriaBanco(Context context){
        super(context, Nome_banco, null, version);
    }

    @Override public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE usuarios("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "email text,"
                + "senha text)";
        db.execSQL(sql);

        String sql1 = "CREATE TABLE atletas("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "idade integer,"
                + "altura float,"
                + "peso float,"
                + "sexo text)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS atletas");
        onCreate(db);
    }
}
