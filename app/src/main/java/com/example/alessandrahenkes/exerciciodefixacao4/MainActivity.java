package com.example.alessandrahenkes.exerciciodefixacao4;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */

public class MainActivity extends ListActivity {

    String [] menu = {"Cadastrar","Editar","Buscar","Excluir"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,menu);
        super.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                Intent itCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(itCadastro);
                break;
            case 1:
                Intent itEditar = new Intent(MainActivity.this, BuscarActivity.class);
                itEditar.putExtra("opcao",0);
                startActivity(itEditar);
                break;
            case 2:
                Intent itBuscar = new Intent(MainActivity.this, BuscarActivity.class);
                itBuscar.putExtra("opcao",1);
                startActivity(itBuscar);
                break;
            case 3:
                Intent itExcluir= new Intent(MainActivity.this, BuscarActivity.class);
                itExcluir.putExtra("opcao",2);
                startActivity(itExcluir);
                break;
        }
    }
}

