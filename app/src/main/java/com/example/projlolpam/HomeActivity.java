package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HomeActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Para pegar Nickname do Invocador pelo SharedPreferences
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        String nickname = preferences.getString("Nick", "Forasteiro");

        // Insere o texto
        TextView textView = (TextView) findViewById(R.id.textNickname);
        textView.setText(getString(R.string.text_olainvocador, nickname));
    }
    public void abrirPorofessor(View view)
    {
        Uri uri = Uri.parse("https://porofessor.gg/pt/");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(Intent.createChooser(it, "Selecione um navegador"));

    }

    public void enviarEmail(View view) throws UnsupportedEncodingException {
        String uriText =
                "mailto:leagueoflegends@support.lol" +
                        "?subject=" + URLEncoder.encode("Ticket", "utf-8") +
                        "&body=" + URLEncoder.encode("Fale%20aqui%20com%20o%20nosso%20suporte!" +
                        "%20Mande-nos%20recomendações%20e%20reporte%20falhas", "utf-8");
        Uri uri = Uri.parse(uriText);
        Intent it = new Intent(Intent.ACTION_SENDTO);
        it.setData(uri);
        startActivity(Intent.createChooser(it, "Enviar Ticket"));
    }

    public void enviarWhatsapp(View view){
        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_TEXT, "Olá Invocador. Você já conhece esse aplicativo " +
                "para te auxiliar a subir de ELO? Confira já! " +
                "https://play.google.com/store/apps/leagueoflegends");
        it.setType("text/plain");
        it.setPackage("com.whatsapp");
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivity(it);
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