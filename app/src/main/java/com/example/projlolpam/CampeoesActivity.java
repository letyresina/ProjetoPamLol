package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class CampeoesActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeoes);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getBoolean("Dark", false)) {
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
            imgbtnItens.setImageResource(R.drawable.imgitens);
            imgbtnPerfil.setImageResource(R.drawable.imgperfil);
            imgBackground.setImageResource(R.drawable.imggwen);
            scrollContainer.setBackgroundResource(R.color.preto_container);
        }
    }

    public void abrirKalistaActivity(View view) {
        Intent intent = new Intent(this, KalistaActivity.class);
        startActivity(intent);
    }

    public void abrirKatarinaActivity(View view) {
        Intent intent = new Intent(this, KatarinaActivity.class);
        startActivity(intent);
    }

    // Navegação pelas Activities

    public void abrirHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }

    public void abrirCampeoesActivity(View view) {
        Intent intent = new Intent(this, CampeoesActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }

    public void abrirItensActivity(View view) {
        Intent intent = new Intent(this, ItensActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }

    public void abrirPerfilActivity(View view) {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
        finish();
    }
}