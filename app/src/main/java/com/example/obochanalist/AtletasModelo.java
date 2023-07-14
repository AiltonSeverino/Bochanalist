package com.example.obochanalist;

public class AtletasModelo {
    int codigo;
    String nome;
    int idade;
    float altura;
    float peso;
    String sexo;

    public AtletasModelo(){
    }

    public AtletasModelo(int codigo, String nome, int idade, float altura, float peso, String sexo){
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
    }

    public int getCodigo() { return codigo;}
    public void setCodigo(int codigo){this.codigo = codigo;}

    public String getNome() {return nome;}
    public void setNome(String nome){this.nome = nome;}

    public int getIdade() {return idade;}
    public void setIdade(int idade){this.idade = idade;}

    public float getAltura() {return altura;}
    public void setAltura(float altura){this.altura = altura;}

    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}
}
