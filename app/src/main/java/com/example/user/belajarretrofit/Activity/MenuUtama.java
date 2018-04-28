package com.example.user.belajarretrofit.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.belajarretrofit.R;

public class MenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
    }


    public void untuk_get_data(View view){

        Intent numbersIntent = new Intent(MenuUtama.this, MainActivity.class);

        // Start the new activity
        startActivity(numbersIntent);
    }

        public void untuk_insert_data(View view){

            Intent Intent = new Intent(MenuUtama.this, InsertData.class);

            // Start the new activity
            startActivity(Intent);
        }

}
