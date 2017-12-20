package com.example.alessandrahenkes.exerciciodefixacao4;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */

public class BuscarActivity extends ListActivity {

    private int opcao;
    private Retrofit retrofit;
    private ServiceVeiculo service;
    private List<Veiculo> listaVeiculo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opcao = super.getIntent().getIntExtra("opcao",0);
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.241.201:8080/compubras/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(ServiceVeiculo.class);

        this.buscarTodos();

    }

    private void buscarTodos(){
        this.service.buscarTodos().enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Call<List<Veiculo>> call, Response<List<Veiculo>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(BuscarActivity.this, "Sucessooo", Toast.LENGTH_SHORT);
                    listaVeiculo = response.body();
                    ArrayAdapter<Veiculo> adapterBuscar = new ArrayAdapter<Veiculo>(BuscarActivity.this, android.R.layout.simple_list_item_1, listaVeiculo);
                    setListAdapter(adapterBuscar);

                }else{
                    Toast.makeText(BuscarActivity.this, "Não deu :c", Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(BuscarActivity.this, "Erro!", Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.buscarTodos();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(opcao == 0){
            Intent itEditar = new Intent(BuscarActivity.this, EditarActivity.class);
            itEditar.putExtra("veiculo", listaVeiculo.get(position));
            startActivity(itEditar);
        }else{
            if(opcao == 2) {
                Intent itExcluir = new Intent(BuscarActivity.this, ExcluirActivity.class);
                itExcluir.putExtra("veiculo", listaVeiculo.get(position));
                startActivity(itExcluir);
            }
        }
    }
}

