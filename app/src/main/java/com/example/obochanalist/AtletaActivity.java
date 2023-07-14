package com.example.obochanalist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class AtletaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtCodigo;
    int codigo;
    Button btnAlter, btnRemove, btnAdd;
    ListView listas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atleta);

        edtCodigo = findViewById(R.id.editTextId);

        btnAlter = findViewById(R.id.btnAlterar);
        btnRemove = findViewById(R.id.btnRemover);
        btnAdd = findViewById(R.id.btnAdicionar);

        btnAlter.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        List<AtletasModelo> listaAtletas = null;
        listaAtletas = consultarTodosAtletas();

        AtletasAdapter adaptador = new AtletasAdapter(this, listaAtletas);

        listas = findViewById(R.id.lista);
        listas.setAdapter(adaptador);
    }

    public List<AtletasModelo> consultarTodosAtletas(){
        List<AtletasModelo> lista = new LinkedList<>();

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.carregaTodosDados();

        if(dados != null && dados.moveToFirst()){
            do{
                AtletasModelo item = new AtletasModelo();
                item.setCodigo(dados.getInt(0));
                item.setNome(dados.getString(1));
                item.setIdade(dados.getInt(2));
                item.setAltura(dados.getFloat(3));
                item.setPeso(dados.getFloat(4));
                item.setSexo(dados.getString(5));
                lista.add(item);
            } while (dados.moveToNext());
        }else{
            String atletaNome = "Exemplo";
            int atletaIdade = 20;
            float atletaAltura = 170;
            float atletaPeso = 56;
            String atletaSexo = "Masculino";

            BancoController bds = new BancoController(getBaseContext());
            String resultado;

            resultado = bds.inserirDadosAtletaTeste(atletaNome, atletaIdade, atletaAltura, atletaPeso, atletaSexo);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }
        return lista;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdicionar){
            Intent Alteracao = new Intent(this, adicionarAtleta.class);
            startActivity(Alteracao);
        }

        if (v.getId() == R.id.btnAlterar){
            if (edtCodigo.length() == 0){
                String msg = "Por favor digite o código de um atleta para Remover ou Alterar";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }else {
                Bundle parametros = new Bundle();
                codigo = Integer.parseInt(edtCodigo.getText().toString());
                parametros.putInt("codigo", codigo);
                Intent mudar = new Intent(this, alterarAtleta.class);

                mudar.putExtras(parametros);
                startActivity(mudar);
            }
        }

        if (v.getId() == R.id.btnRemover){
            excluir();
        }
    }

    public void excluir(){
        if (edtCodigo.length() == 0){
            String msg = "Por favor digite o código de um atleta para Remover ou Alterar";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }else {
            codigo = Integer.parseInt(edtCodigo.getText().toString());
        }
        BancoController bd = new BancoController(getBaseContext());

        String res;
        res = bd.excluirDados(codigo);

        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
    }
}