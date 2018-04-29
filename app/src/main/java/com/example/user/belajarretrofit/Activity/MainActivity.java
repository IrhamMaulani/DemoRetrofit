package com.example.user.belajarretrofit.Activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.belajarretrofit.Adapter.KonsumenAdapter;
import com.example.user.belajarretrofit.Model.Konsumen;
import com.example.user.belajarretrofit.Network.ApiInterface;
import com.example.user.belajarretrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SwipeRefreshLayout SwipeRefresh;
    //private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_all);

        listView = (ListView) findViewById(R.id.list);





        //Mulai untuk Get data,intent update data dan delete data
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.69/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<Konsumen>> call = client.getUser();
        call.enqueue(new Callback<List<Konsumen>>() {
            @Override
            public void onResponse(Call<List<Konsumen>> call, Response<List<Konsumen>> response) {
                if (response.isSuccessful()) {
                    final List<Konsumen> repos = response.body();

                    listView.setAdapter(new KonsumenAdapter(MainActivity.this, repos));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            Konsumen konsumen = repos.get(position);
                            // Intent intent = new Intent(MainActivity.this, UpdateData.class);
                            //intent.putExtra("EXTRA_SESSION_ID", konsumen.getIdkonsumen());

                            //String message="Terpilih : " + konsumen.getIdkonsumen();
                            // Toast.makeText(MainActivity.this, "aa:(" + message, Toast.LENGTH_SHORT).show();

                            // Start the new activity
                            //startActivity(intent);
                            Bundle b = new Bundle();
                            b.putStringArray("List", new String[]{konsumen.getNamakonsumen(), konsumen.getAlamatkonsumen(),konsumen.getIdkonsumen()});
                            Intent i=new Intent(MainActivity.this   , UpdateData.class);
                            i.putExtras(b);
                            startActivity(i);

                        }
                    });

                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                       final int position, long id) {
                            // TODO Auto-generated method stub
                            final Konsumen konsumen = repos.get(position);

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this );
                            builder.setCancelable(true);
                            // builder.setTitle("Apakah Anda Ingin Menghapus Data ini?");
                            builder.setMessage("Apakah Anda Ingin Menghapus Data ini?");
                            builder.setPositiveButton("Confirm",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            Retrofit.Builder builder = new Retrofit.Builder()
                                                    .baseUrl("http://192.168.1.69/")
                                                    .addConverterFactory(GsonConverterFactory.create());

                                            Retrofit retrofit = builder.build();
                                            ApiInterface client = retrofit.create(ApiInterface.class);
                                            Call<Konsumen> call = client.deleteData(konsumen.getIdkonsumen());



                                            call.enqueue(new Callback<Konsumen>() {
                                                @Override
                                                public void onResponse(Call<Konsumen> call, Response<Konsumen> response) {
                                                    Toast.makeText(MainActivity.this, "Data telah di di hapus " + response.body().getResponseServer(), Toast.LENGTH_SHORT).show();
                                                    refresh();
                                                    //untuk reset position
                                                    repos.remove(position);
                                                    //this..notifyDataSetChanged();


                                                }

                                                @Override
                                                public void onFailure(Call<Konsumen> call, Throwable t) {

                                                    Toast.makeText(MainActivity.this, "Gagal menhapus Data :(" , Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                        }
                                    });
                            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, "SA :(", Toast.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();



                            return true;
                        }

                    });

                }

                else if(response.code() == 400){
                    Toast.makeText(MainActivity.this, "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Konsumen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();

            }
        });




        //Akhir dari getdata,intent update dan delete



        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = findViewById(R.id.swipe_refresh);
        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler digunakan untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Berhenti berputar/refreshing
                        SwipeRefresh.setRefreshing(false);

                        //Berganti Text Setelah Layar di Refresh
                        refresh();

                    }
                },2000); //4000 millisecond = 4 detik
            }
        });


        // button = (Button) findViewById(R.id.untukRefresh);

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refresh();
            }
        });*/



    }

    public void refresh(){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.69/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<List<Konsumen>> call = client.getUser();
        call.enqueue(new Callback<List<Konsumen>>() {
            @Override
            public void onResponse(Call<List<Konsumen>> call, Response<List<Konsumen>> response) {
                if (response.isSuccessful()) {
                    final List<Konsumen> repos = response.body();

                    listView.setAdapter(new KonsumenAdapter(MainActivity.this, repos));

                }

                else if(response.code() == 400){
                    Toast.makeText(MainActivity.this, "Server busuk :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Konsumen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onPostResume() {
        refresh();
        super.onPostResume();
    }
}
