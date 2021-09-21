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

import org.w3c.dom.Text;

public class KalistaActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalista);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getBoolean("Dark", false)) {
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
            TextView textTituloCampeao = (TextView) findViewById(R.id.textTituloCampeao);
            TextView textFraseCampeao = (TextView) findViewById(R.id.textFraseCampeao);
            TextView textBuildGeral = (TextView) findViewById(R.id.textBuildGeral);
            TextView textRunaGeral = (TextView) findViewById(R.id.textRunaGeral);
            TextView textMecanica = (TextView) findViewById(R.id.textMecanica);
            TextView textSobre = (TextView) findViewById(R.id.textSobre);
            LinearLayout linearDivisao1 = (LinearLayout) findViewById(R.id.linearDivisao1);
            LinearLayout linearDivisao2 = (LinearLayout) findViewById(R.id.linearDivisao2);
            LinearLayout linearDivisao3 = (LinearLayout) findViewById(R.id.linearDivisao3) ;

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
            imgbtnItens.setImageResource(R.drawable.imgitens);
            imgbtnPerfil.setImageResource(R.drawable.imgperfil);
            imgBackground.setImageResource(R.drawable.imggwen);
            scrollContainer.setBackgroundResource(R.color.preto_container);
            textTituloCampeao.setTextColor(getResources().getColor(R.color.branco_tit_borda));
            textFraseCampeao.setTextColor(getResources().getColor(R.color.branco_texto));
            textBuildGeral.setTextColor(getResources().getColor(R.color.branco_texto));
            textRunaGeral.setTextColor(getResources().getColor(R.color.branco_tit_borda));
            textMecanica.setTextColor(getResources().getColor(R.color.branco_tit_borda));
            textSobre.setTextColor(getResources().getColor(R.color.branco_texto));
            linearDivisao1.setBackgroundResource(R.color.branco_topocontainer);
            linearDivisao2.setBackgroundResource(R.color.branco_topocontainer);
            linearDivisao3.setBackgroundResource(R.color.branco_topocontainer);
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