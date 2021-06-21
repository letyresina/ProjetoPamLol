package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NICKNAME);

        // Cria um elemento text view
        TextView textView = new TextView(this);
        textView.setTextSize(24);
        textView.setText(message);

        // Define o text view com layout ativo
        // setContentView(textView);


    }
    public void abrirPorofessor(View view)
    {
        Uri uri = Uri.parse("https://masp.org.br");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(Intent.createChooser(it, "Selecione um navegador"));

    }

    public void enviarEmail(View view) throws UnsupportedEncodingException {
        String uriText =
                "mailto:leagueoflegends@support.lol" +
                        "?subject=" + URLEncoder.encode("Ticket - ", "utf-8") +
                        "&body=" + URLEncoder.encode("Fale aqui com o nosso suporte! Mande-nos" +
                                                        "recomendações e reporte falhas", "utf-8");
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
}