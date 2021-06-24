package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ArcoEscudoImortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arco_escudo_imortal);
    }

    public void abrirHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void abrirCampeoesActivity(View view) {
        Intent intent = new Intent(this, CampeoesActivity.class);
        startActivity(intent);
    }
}