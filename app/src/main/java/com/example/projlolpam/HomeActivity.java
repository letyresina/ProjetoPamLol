package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

        if (preferences.getBoolean("Dark", false)){
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
            TextView textTituloSobre = (TextView) findViewById(R.id.textTituloSobre);
            TextView textNickname = (TextView) findViewById(R.id.textNickname);
            TextView textSobre = (TextView) findViewById(R.id.textSobre);

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
            imgbtnItens.setImageResource(R.drawable.imgitens);
            imgbtnPerfil.setImageResource(R.drawable.imgperfil);
            imgBackground.setImageResource(R.drawable.imggwen);
            scrollContainer.setBackgroundResource(R.color.preto_container);
            textTituloSobre.setTextColor(getResources().getColor(R.color.branco_tit_borda));
            textNickname.setTextColor(getResources().getColor(R.color.branco_texto));
            textSobre.setTextColor(getResources().getColor(R.color.branco_texto));
        }
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