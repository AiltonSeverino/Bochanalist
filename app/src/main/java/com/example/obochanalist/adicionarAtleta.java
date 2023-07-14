package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class adicionarAtleta extends AppCompatActivity implements View.OnClickListener{

    EditText edtTxtAltNome, edtTxtAltIdade, edtTxtAltAltura, edtTxtAltPeso;
    CheckBox checkBoxM, checkBoxF;
    Button btnAltAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_atleta);

        edtTxtAltNome = findViewById(R.id.editTextAtlNome);
        edtTxtAltIdade = findViewById(R.id.editTextAtlIdade);
        edtTxtAltAltura = findViewById(R.id.editTextAtlAltura);
        edtTxtAltPeso = findViewById(R.id.editTextAtlPeso);
        checkBoxM = findViewById(R.id.checkBoxM);
        checkBoxF = findViewById(R.id.checkBoxF);
        btnAltAdd = findViewById(R.id.btnAtlAdicionar);

        btnAltAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String atletaNome = edtTxtAltNome.getText().toString();
        int atletaIdade = Integer.parseInt(edtTxtAltIdade.getText().toString());
        float atletaAltura = Float.parseFloat(edtTxtAltAltura.getText().toString());
        float atletaPeso = Float.parseFloat(edtTxtAltPeso.getText().toString());
        String atletaSexo;

        if (checkBoxM.isChecked()){
            atletaSexo = checkBoxM.getText().toString();
        } else {
            atletaSexo = checkBoxF.getText().toString();
        }

        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        if(atletaNome.length() == 0){

            String msg = "Coloque o nome do atleta!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            edtTxtAltNome.requestFocus();
        } else{
            if(atletaIdade == 0){

                String msg = "Coloque a idade do atleta!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                edtTxtAltIdade.requestFocus();
            } else {
                if(atletaAltura == 0){

                    String msg = "Coloque a altura do atleta!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    edtTxtAltAltura.requestFocus();
                } else {
                    if(atletaPeso == 0){

                        String msg = "Coloque o Peso do atleta!";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        edtTxtAltPeso.requestFocus();
                    } else{

                        resultado = bd.inserirDadosAtleta(atletaNome, atletaIdade, atletaAltura, atletaPeso, atletaSexo);

                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                        limpar();

                        Intent voltar = new Intent(this, AtletaActivity.class);
                        startActivity(voltar);
                    }
                }

            }
        }
    }

    public void limpar(){
        edtTxtAltNome.setText("");
        edtTxtAltIdade.setText("");
        edtTxtAltAltura.setText("");
        edtTxtAltPeso.setText("");
    }
}