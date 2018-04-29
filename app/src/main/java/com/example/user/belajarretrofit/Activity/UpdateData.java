package com.example.user.belajarretrofit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.belajarretrofit.Model.Konsumen;
import com.example.user.belajarretrofit.Network.ApiInterface;
import com.example.user.belajarretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Bundle b = this.getIntent().getExtras();
        String[] array=b.getStringArray("List");

        final EditText inputNama = (EditText) findViewById(R.id.input_nama);
        final EditText inputAlamat = (EditText) findViewById(R.id.input_alamat);

        assert array != null;
        String namaKonsumen = array[0];
        String alamatKonsumen = array[1];
        final String idKonsumen = array[2];

        inputNama.setText(namaKonsumen);
        inputAlamat.setText(alamatKonsumen);

        Button inputData = (Button) findViewById(R.id.button_for_submit);

        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Konsumen konsumen = new Konsumen(inputNama.getText().toString(),inputAlamat.getText().toString());

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.69/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                ApiInterface client = retrofit.create(ApiInterface.class);
                Call<Konsumen> call = client. updateData(idKonsumen,inputNama.getText().toString(),inputAlamat.getText().toString());

                call.enqueue(new Callback<Konsumen>() {
                    @Override
                    public void onResponse(Call<Konsumen> call, Response<Konsumen> response) {
                        Toast.makeText(UpdateData.this, "Data telah di update " + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                        Log.v("cek sasja","isi dari konsumen" +response.body().getResponseServer() );

                    }

                    @Override
                    public void onFailure(Call<Konsumen> call, Throwable t) {

                        Toast.makeText(UpdateData.this, "Gagal memasukkan Data :(" , Toast.LENGTH_SHORT).show();
                        Log.v("Coba","isi dari konsumen" +inputAlamat.getText().toString() );
                    }
                });

            }
        });


    }
}
