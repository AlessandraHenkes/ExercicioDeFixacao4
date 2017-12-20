package com.example.alessandrahenkes.exerciciodefixacao4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */

public class EditarActivity extends Activity {

    private EditText etPlaca;
    private EditText etCor;
    private Spinner spMarca;
    private RadioGroup rgEstado;
    private RadioButton rbNovo;
    private RadioButton rbSemiNovo;
    private Button btOpcao;
    private int positionMarca;

    String [] marcas = {"Chevrolet","Fiat","Ford","Volkswagen"};

    private Retrofit retrofit;
    private ServiceVeiculo service;
    private Veiculo veiculo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        veiculo = (Veiculo) super.getIntent().getSerializableExtra("veiculo");

        this.inicializarComponentes();
    }

    private void inicializarComponentes(){

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.241.201:8080/compubras/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(ServiceVeiculo.class);

        this.etPlaca = (EditText) findViewById(R.id.et_placa);
        this.etCor = (EditText) findViewById(R.id.et_cor);
        this.spMarca = (Spinner) findViewById(R.id.sp_marca);
        this.rbSemiNovo = (RadioButton) findViewById(R.id.rb_semi_novo);
        this.rbNovo = (RadioButton) findViewById(R.id.rb_novo);
        this.btOpcao = (Button) findViewById(R.id.bt_opcao);

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_list_item_1,marcas);
        this.spMarca.setAdapter(adapterSpinner);


        this.etPlaca.setText(veiculo.getPlaca());
        this.etCor.setText(veiculo.getCor());

        for(int i = 0; i < marcas.length; i++){
            if(veiculo.getMarca().equals(marcas[i])){
                positionMarca = i;
            }
        }

        this.spMarca.setSelection(positionMarca);
        this.rbNovo.setChecked(veiculo.getNovo());
        this.rbSemiNovo.setChecked(!veiculo.getNovo());
        this.btOpcao.setText("EDITAR");

        this.eventosComponentes();

    }

    private void eventosComponentes(){

        this.btOpcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veiculo.setPlaca(etPlaca.getText().toString());
                veiculo.setCor(etCor.getText().toString());
                veiculo.setMarca(spMarca.getSelectedItem().toString());
                veiculo.setNovo(rbNovo.isChecked());

                service.editarVeiculo(veiculo).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(EditarActivity.this, "Editado com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(EditarActivity.this, "Algo de errado aconteceu!", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(EditarActivity.this, "Falha ao editar.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
