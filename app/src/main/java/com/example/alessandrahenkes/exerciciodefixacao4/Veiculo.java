package com.example.alessandrahenkes.exerciciodefixacao4;

import java.io.Serializable;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */
public class Veiculo implements Serializable {
    private long id;
    private String placa;
    private String cor;
    private String marca;
    private boolean novo;

    public Veiculo() {
    }

    public Veiculo(String placa, String cor, String marca, boolean novo) {
        this.placa = placa;
        this.cor = cor;
        this.marca = marca;
        this.novo = novo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean getNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    @Override
    public String toString() {
        String estado = (novo) ? "Novo":"Semi-novo";
        return placa+":"+cor+":"+marca+":"+estado;
    }
}
