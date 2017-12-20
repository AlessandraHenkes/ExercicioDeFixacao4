package com.example.alessandrahenkes.exerciciodefixacao4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Alessandra Henkes on 20/12/2017.
 */

public interface ServiceVeiculo {

    @POST("veiculo/")
    Call<Veiculo> cadastraVeiculo(@Body Veiculo veiculo);

    @GET("veiculo/")
    Call<List<Veiculo>> buscarTodos();

    //não está pronto
    @PUT("veiculo/")
    Call<Void> editarVeiculo(@Body Veiculo veiculo);

    @DELETE("veiculo/{id}")
    Call<Void> excluirVeiculo(@Path("id") long id);

}