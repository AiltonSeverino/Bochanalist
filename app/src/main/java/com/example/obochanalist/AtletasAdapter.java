package com.example.obochanalist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AtletasAdapter extends ArrayAdapter<AtletasModelo> {

    private Context context;
    private List<AtletasModelo> listaAtletas = null;

    public AtletasAdapter(Context context, List<AtletasModelo> listaAtletas){
        super(context, 0, listaAtletas);
        this.listaAtletas = listaAtletas;
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent){
        AtletasModelo atleta = listaAtletas.get(position);

        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.item_lista, null);
        }

        TextView txtViewCodigo = v.findViewById(R.id.codigo);
        txtViewCodigo.setText(String.format("Id: %s", atleta.getCodigo()));

        TextView txtViewNome = v.findViewById(R.id.nome);
        txtViewNome.setText(atleta.getNome());

        TextView txtViewIdade = v.findViewById(R.id.idade);
        txtViewIdade.setText(String.format("%s anos",atleta.getIdade()));

        TextView txtViewAltura = v.findViewById(R.id.altura);
        txtViewAltura.setText(String.format("%sm",atleta.getAltura()));

        TextView txtViewPeso = v.findViewById(R.id.peso);
        txtViewPeso.setText(String.format("%sKg",atleta.getPeso()));

        TextView txtViewSexo = v.findViewById(R.id.sexo);
        txtViewSexo.setText(atleta.getSexo());

        return v;
    }
}
