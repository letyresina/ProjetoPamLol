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
import android.widget.TextView;

public class DenteDeNashorActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dente_de_nashor);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getBoolean("Dark", false)) {
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
            TextView textTituloItem = (TextView) findViewById(R.id.textTituloItem);
            TextView textSobre = (TextView) findViewById(R.id.textSobre);
            TextView textCampeoesRecomendados = (TextView) findViewById(R.id.textCampeoesRecomendados);
            LinearLayout linearDivisao1 = (LinearLayout) findViewById(R.id.linearDivisao1);

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setBackgroundResource(R.drawable.imgcampeoes);
            imgbtnItens.setBackgroundResource(R.drawable.imgitens);
            imgbtnPerfil.setBackgroundResource(R.drawable.imgperfil);
            imgBackground.setBackgroundResource(R.drawable.imggwen);
            scrollContainer.setBackgroundResource(R.color.preto_container);
            textTituloItem.setBackgroundResource(R.color.branco_texto);
            textSobre.setBackgroundResource(R.color.branco_texto);
            textCampeoesRecomendados.setBackgroundResource(R.color.branco_texto);
            linearDivisao1.setBackgroundResource(R.color.branco_topocontainer);
        }
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