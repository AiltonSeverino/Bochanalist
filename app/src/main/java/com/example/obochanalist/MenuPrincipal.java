package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button btnPartida, btnAtleta;
    TextView txtPNome;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Intent intencao = getIntent();
        Bundle parametros = intencao.getExtras();
        nome = parametros.getString("nome");

        txtPNome = findViewById(R.id.textViewPNome);
        txtPNome.setText("Bem vindo(a) Treinador(a) " + nome);

        btnPartida = findViewById(R.id.btnPartidas);
        btnAtleta = findViewById(R.id.btnAtleta);

        btnPartida.setOnClickListener(this);
        btnAtleta.setOnClickListener(this);
    }

    public void onClick(View v) {

        if(v.getId() == R.id.btnAtleta){
            Intent atletas = new Intent(this, AtletaActivity.class);
            startActivity(atletas);
        }
    }
}