package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistrar;
    EditText edtTxtNomeCad, edtTxtEmailCad, edtTxtSenhaCad, edtTxtConfSenhaCad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnRegistrar =  findViewById(R.id.btnRegistrar);
        edtTxtNomeCad =  findViewById(R.id.edtTxtNomeCad);
        edtTxtEmailCad =  findViewById(R.id.edtTxtEmailCad);
        edtTxtSenhaCad =  findViewById(R.id.edtTxtSenhaCad);
        edtTxtConfSenhaCad =  findViewById(R.id.edtTxtConfSenhaCad);

        btnRegistrar.setOnClickListener(this);
    }

    public void onClick(View v){
        String NomeCad = edtTxtNomeCad.getText().toString();
        String EmailCad = edtTxtEmailCad.getText().toString();
        String SenhaCad = edtTxtSenhaCad.getText().toString();
        String ConfSenhaCad = edtTxtConfSenhaCad.getText().toString();


        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        if(NomeCad.length() == 0){

            String msg = "Preencha o campo nome!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            edtTxtNomeCad.requestFocus();
        }else {
            if (EmailCad.length() == 0) {

                String msg = "Preencha o campo email!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                edtTxtEmailCad.requestFocus();

            } else {
                if (SenhaCad.equals(ConfSenhaCad)) {
                    resultado = bd.inserirDadosUsuario(NomeCad, EmailCad, SenhaCad);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    limpar();

                    Intent voltar = new Intent(this, MainActivity.class);
                    startActivity(voltar);

                } else {
                    String msg = "As senha digitadas não conferem. Tente Novamente!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void limpar(){
        edtTxtNomeCad.setText("");
        edtTxtEmailCad.setText("");
        edtTxtSenhaCad.setText("");
        edtTxtConfSenhaCad.setText("");
        edtTxtNomeCad.requestFocus();
    }
}