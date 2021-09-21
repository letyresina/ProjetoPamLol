package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Para pegar Nickname e Regiao do Invocador pelo SharedPreferences
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();
        String nickname = preferences.getString("Nick", "Forasteiro");
        String regiao = preferences.getString("Regiao", "Runeterra");

        // Insere o texto
        TextView textNickname = (TextView) findViewById(R.id.textNicknameInvocador);
        TextView textRegiao = (TextView) findViewById(R.id.textRegiaoInvocador);
        textNickname.setText(getString(R.string.invocador_nickname, nickname));
        textRegiao.setText(getString(R.string.invocador_regiao, regiao));


        Switch switchTema = (Switch) findViewById(R.id.switchTema);
        if(preferences.contains("Dark")) {
            switchTema.setChecked(preferences.getBoolean("Dark", false));
        }
        switchTema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("Dark", true);
                } else {
                    editor.putBoolean("Dark", false);
                }
                editor.commit();
            }
        });

        if (preferences.getBoolean("Dark", false)){
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
            TextView textNicknameInvocador = (TextView) findViewById(R.id.textNicknameInvocador);
            TextView textRegiaoInvocador = (TextView) findViewById(R.id.textRegiaoInvocador);
            TextView textConfiguracaoGeral = (TextView) findViewById(R.id.textConfiguracaoGeral);

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setBackgroundResource(R.drawable.imgcampeoes);
            imgbtnItens.setBackgroundResource(R.drawable.imgitens);
            imgbtnPerfil.setBackgroundResource(R.drawable.imgperfil);
            imgBackground.setBackgroundResource(R.drawable.imggwen);
            scrollContainer.setBackgroundResource(R.color.preto_container);
            textNicknameInvocador.setBackgroundResource(R.color.branco_texto);
            textRegiaoInvocador.setBackgroundResource(R.color.branco_texto);
            textConfiguracaoGeral.setBackgroundResource(R.color.branco_texto);
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