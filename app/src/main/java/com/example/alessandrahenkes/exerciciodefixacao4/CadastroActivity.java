package com.example.alessandrahenkes.exerciciodefixacao4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */

public class CadastroActivity extends AppCompatActivity {

    private EditText etPlaca;
    private EditText etCor;
    private Spinner spMarca;
    private RadioGroup rgEstado;
    private RadioButton rbNovo;
    private Button btOpcao;

    String [] marcas = {"Chevrolet","Fiat","Ford","Volkswagen"};

    private Retrofit retrofit;
    private ServiceVeiculo service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
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
        this.rbNovo = (RadioButton) findViewById(R.id.rb_novo);
        this.btOpcao = (Button) findViewById(R.id.bt_opcao);

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(CadastroActivity.this, android.R.layout.simple_list_item_1,marcas);
        this.spMarca.setAdapter(adapterSpinner);
        this.btOpcao.setText("CADASTRAR");

        this.eventosComponentes();
    }

    private void eventosComponentes(){
        this.btOpcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Veiculo veiculo = new Veiculo(etPlaca.getText().toString(),
                        etCor.getText().toString(),
                        spMarca.getSelectedItem().toString(),
                        rbNovo.isSelected());
                //Toast.makeText(CadastroActivity.this, veiculo.toString(), Toast.LENGTH_SHORT).show();

                service.cadastraVeiculo(veiculo).enqueue(new Callback<Veiculo>() {
                    @Override
                    public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(CadastroActivity.this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT ).show();
                        }else{
                            Toast.makeText(CadastroActivity.this,"Houve um erro!", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Veiculo> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(CadastroActivity.this,"Falha na comunicação! ["+t.getCause()+"]", Toast.LENGTH_SHORT ).show();
                    }
                });


            }
        });
    }
}
