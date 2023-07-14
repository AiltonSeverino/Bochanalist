package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class alterarAtleta extends AppCompatActivity implements View.OnClickListener{

    EditText edtNewNome, edtNewIdade, edtNewAltura, edtNewPeso;
    Button btnNewAlterar;
    int codigo ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_atleta);

        Intent intencao = getIntent();
        Bundle parametros = intencao.getExtras();
        codigo = parametros.getInt("codigo");

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.carregarDadoPeloCodigo(codigo);

        edtNewNome = findViewById(R.id.editTextNnome);
        edtNewIdade = findViewById(R.id.editTextNidade);
        edtNewAltura = findViewById(R.id.editTextNaltura);
        edtNewPeso = findViewById(R.id.editTextNpeso);
        btnNewAlterar = findViewById(R.id.btnNalterar);

        if(dados.moveToFirst()){
            edtNewNome.setText(dados.getString(1));
            edtNewIdade.setText(String.valueOf(dados.getInt(2)));
            edtNewAltura.setText(String.valueOf(dados.getFloat(3)));
            edtNewPeso.setText(String.valueOf(dados.getFloat(4)));
        } else{
            String msg = "Código não cadastrado";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }

        btnNewAlterar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        alterar();
    }

    public void alterar(){
        int id = Integer.parseInt(String.valueOf(codigo));
        String nome = edtNewNome.getText().toString();
        int idade = Integer.parseInt(edtNewIdade.getText().toString());
        float altura = Float.parseFloat(edtNewAltura.getText().toString());
        float peso = Float.parseFloat(edtNewPeso.getText().toString());

        BancoController bd = new BancoController(getBaseContext());

        String msg;
        msg = bd.alteraDados(id, nome, idade, altura, peso);

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        Intent volta = new Intent(this, AtletaActivity.class);
        startActivity(volta);
    }
}