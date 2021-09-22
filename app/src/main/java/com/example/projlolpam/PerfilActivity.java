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
import android.widget.Toast;

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

                Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (preferences.getBoolean("Dark", false)){
            LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
            ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
            ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
            ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
            ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
            TextView textConfiguracaoGeral = (TextView) findViewById(R.id.textConfiguracaoGeral);

            linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
            imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
            imgbtnItens.setImageResource(R.drawable.imgitens);
            imgbtnPerfil.setImageResource(R.drawable.imgperfil);
            imgBackground.setImageResource(R.drawable.imggwen);
            imgBackground.setScrollX(-240);
            scrollContainer.setBackgroundResource(R.color.preto_container);
            textNickname.setTextColor(getResources().getColor(R.color.branco_texto));
            textRegiao.setTextColor(getResources().getColor(R.color.branco_texto));
            textConfiguracaoGeral.setTextColor(getResources().getColor(R.color.branco_texto));
            switchTema.setTextColor(getResources().getColor(R.color.branco_texto));
        }
    }

    public void deslogar(View view){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        preferences.edit().clear().commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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