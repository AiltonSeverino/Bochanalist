package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEntrar, btnCadastro;
    EditText edtTxtLogin, edtTxtPassword;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar =  findViewById(R.id.btnEntrar);
        btnCadastro =  findViewById(R.id.btnCadastro);
        edtTxtLogin =  findViewById(R.id.edtTxtLogin);
        edtTxtPassword =  findViewById(R.id.edtTxtPassword);

        btnEntrar.setOnClickListener(this);
        btnCadastro.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnCadastro){
            Intent telaCadastro = new Intent(this, CadastroActivity.class);
            startActivity(telaCadastro);
        }
        if (v.getId() == R.id.btnEntrar){
            consultaLogin();
        }
    }
    public void consultaLogin(){
        String Login = edtTxtLogin.getText().toString();
        String Senha = edtTxtPassword.getText().toString();

        BancoController bd = new BancoController(getBaseContext());

        Cursor dados = bd.carregarDadosLogin(Login, Senha);

        if (dados.moveToFirst()){
            String nome = dados.getString(1);
            Bundle parametros = new Bundle();
            parametros.putString("nome", nome);
            Intent tela = new Intent(this, MenuPrincipal.class);

            tela.putExtras(parametros);
            startActivity(tela);
        } else{
            String msg = "Usuário ou Senha incorreta, Tente novamente!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            limpar();
        }
    }

    public void limpar(){
        edtTxtLogin.setText("");
        edtTxtPassword.setText("");
        edtTxtLogin.requestFocus();
    }
}