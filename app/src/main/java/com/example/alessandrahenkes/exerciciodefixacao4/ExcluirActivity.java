package com.example.alessandrahenkes.exerciciodefixacao4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ExcluirActivity extends AppCompatActivity {

    private TextView tvPlaca;
    private TextView tvCor;
    private TextView tvMarca;
    private TextView tvNovo;
    private Button btExcluir;

    private Retrofit retrofit;
    private ServiceVeiculo service;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
        this.veiculo = (Veiculo) super.getIntent().getSerializableExtra("veiculo");
        this.inicializarComponentes();
    }

    private void inicializarComponentes(){

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.241.201:8080/compubras/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(ServiceVeiculo.class);

        this.tvPlaca = (TextView) findViewById(R.id.tv_placa);
        this.tvCor = (TextView) findViewById(R.id.tv_cor);
        this.tvMarca = (TextView) findViewById(R.id.tv_marca);
        this.tvNovo = (TextView) findViewById(R.id.tv_novo);
        this.btExcluir = (Button) findViewById(R.id.bt_excluir);

        this.tvPlaca.setText(this.veiculo.getPlaca());
        this.tvCor.setText(this.veiculo.getCor());
        this.tvMarca.setText(this.veiculo.getMarca());
        this.tvNovo.setText((this.veiculo.getNovo()) ? "Novo":"Semi-novo");
        this.eventosComponentes();
    }

    private void eventosComponentes(){
        this.btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                service.excluirVeiculo(veiculo.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ExcluirActivity.this, "Veiculo excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ExcluirActivity.this, "Falha ao excluir!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ExcluirActivity.this, "Falha na comunicação.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}

