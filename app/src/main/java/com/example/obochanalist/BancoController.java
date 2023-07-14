package com.example.obochanalist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private final CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public Cursor carregaTodosDados(){
        Cursor cursor;
        String[] campos = {"codigo", "nome", "idade", "altura", "peso", "sexo"};

        db = banco.getReadableDatabase();
        cursor = db.query("atletas", campos, null, null, null,
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public String inserirDadosUsuario(String nome, String email, String senha){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao registrar os dados, tente novamente!";
        } else{
            return "Usuário cadastrado com sucesso!";
        }
    }

    public String inserirDadosAtleta(String nome, Integer idade, Float altura, Float peso, String sexo){
        ContentValues dados;
        long resultado;
        db = banco.getWritableDatabase();

        dados = new ContentValues();
        dados.put("nome", nome);
        dados.put("idade", idade);
        dados.put("altura", altura);
        dados.put("peso", peso);
        dados.put("sexo", sexo);

        resultado = db.insert("atletas", null, dados);
        db.close();

        if (resultado == -1){
            return "Erro ao registrar os dados, tente novamente!";
        } else{
            return "Atleta adicionado com sucesso!";
        }
    }

    public String inserirDadosAtletaTeste(String nome, Integer idade, Float altura, Float peso, String sexo){
        ContentValues dados;
        long resultado;
        db = banco.getWritableDatabase();

        dados = new ContentValues();
        dados.put("nome", nome);
        dados.put("idade", idade);
        dados.put("altura", altura);
        dados.put("peso", peso);
        dados.put("sexo", sexo);

        resultado = db.insert("atletas", null, dados);
        db.close();

        if (resultado == -1){
            return "Erro ao registrar os dados, tente novamente!";
        } else{
            return "Atleta teste adicionado com sucesso!\n Remova-o após adicionar algum atleta!";
        }
    }

    public Cursor carregarDadosLogin(String Login, String Senha){
        Cursor cursor;
        String[] campos = {"codigo", "nome", "email", "senha"};
        String filtro = "email = '" + Login + "'and senha = '" + Senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, filtro, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregarDadoPeloCodigo(int id){
        Cursor cursor;
        String[] campos = {"codigo", "nome", "idade", "altura", "peso", "sexo"};
        String where = "codigo=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("atletas", campos, where, null, null, null,
                null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alteraDados(int id, String nome, int idade, float altura, float peso){
        String msg = "Dados alterados com sucesso!";

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("idade", idade);
        valores.put("altura", altura);
        valores.put("peso", peso);

        String condicao = "codigo = " + id;

        int linha;
        linha = db.update("atletas", valores, condicao, null);
        if(linha < 1){
            msg = "Erro ao alterar os dados";
        }

        db.close();
        return msg;
    }

    public String excluirDados(int id){
        String msg = "Dados Excluidos";

        db= banco.getReadableDatabase();
        String condicao = "codigo = " + id;

        int linhas;
        linhas = db.delete("atletas", condicao, null);

        if(linhas < 1){
            msg = "Erro ao Excluir";
        }

        db.close();
        return msg;
    }
}
