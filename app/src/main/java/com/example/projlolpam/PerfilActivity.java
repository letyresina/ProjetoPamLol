package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity implements SensorEventListener {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";

    SensorManager sensorManager;
    Sensor sensor;
    Float luminosidade;

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
        Switch switchTema2 = (Switch) findViewById(R.id.switchTema2);

        if (preferences.getBoolean("Automatico", false)) {
            switchTema2.setChecked(true);
            switchTema.setClickable(false);
            if (preferences.getBoolean("Dark", false)){
                ativarDarkMode();
            }
        } else if (preferences.getBoolean("Dark", false)) {
            switchTema.setChecked(true);
            switchTema2.setChecked(false);
            ativarDarkMode();
        }

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        switchTema2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("Automatico", true);
                    switchTema.setClickable(false);
                } else {
                    editor.putBoolean("Automatico", false);
                    switchTema.setClickable(false);
                }
                editor.commit();

                Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                startActivity(intent);
                PerfilActivity.this.overridePendingTransition(0, 0);
                finish();
            }
        });

        switchTema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("Dark", true);
                } else {
                    editor.putBoolean("Dark", false);
                }
                editor.commit();

                Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                startActivity(intent);
                PerfilActivity.this.overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getBoolean("Automatico", false)) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                luminosidade = event.values[0];

                if (preferences.getBoolean("Automatico", false) && luminosidade < 20000) {
                    editor.putBoolean("Dark", true).apply();

                    Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    this.overridePendingTransition(0, 0);
                    finish();
                } else if (preferences.getBoolean("Automatico", false) && luminosidade >= 20000) {
                    editor.putBoolean("Dark", false).apply();

                    Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    this.overridePendingTransition(0, 0);
                    finish();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void deslogar(View view){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        preferences.edit().clear().commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ativarDarkMode(){
        LinearLayout linearCabecalho = (LinearLayout) findViewById(R.id.linearCabecalho);
        ImageButton imgbtnCampeoes = (ImageButton) findViewById(R.id.imgbtnCampeoes);
        ImageButton imgbtnItens = (ImageButton) findViewById(R.id.imgbtnItens);
        ImageButton imgbtnPerfil = (ImageButton) findViewById(R.id.imgbtnPerfil);
        ImageView imgBackground = (ImageView) findViewById(R.id.imgBackground);
        ScrollView scrollContainer = (ScrollView) findViewById(R.id.scrollContainer);
        TextView textConfiguracaoGeral = (TextView) findViewById(R.id.textConfiguracaoGeral);
        LinearLayout linearDivisao1 = (LinearLayout) findViewById(R.id.linearDivisao1);
        Button buttonDeslogar = (Button) findViewById(R.id.buttonSalvar);
        TextView textNickname = (TextView) findViewById(R.id.textNicknameInvocador);
        TextView textRegiao = (TextView) findViewById(R.id.textRegiaoInvocador);
        Switch switchTema = (Switch) findViewById(R.id.switchTema);
        Switch switchTema2 = (Switch) findViewById(R.id.switchTema2);

        linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
        imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
        imgbtnItens.setImageResource(R.drawable.imgitens);
        imgbtnPerfil.setImageResource(R.drawable.imgperfil);
        imgBackground.setImageResource(R.drawable.imggwen_fundo);
        imgBackground.setScrollX(-240);
        scrollContainer.setBackgroundResource(R.color.preto_container);
        textNickname.setTextColor(getResources().getColor(R.color.branco_texto));
        textRegiao.setTextColor(getResources().getColor(R.color.branco_texto));
        textConfiguracaoGeral.setTextColor(getResources().getColor(R.color.branco_texto));
        switchTema.setTextColor(getResources().getColor(R.color.branco_texto));
        switchTema2.setTextColor(getResources().getColor(R.color.branco_texto));
        linearDivisao1.setBackgroundResource(R.color.branco_topocontainer);
        buttonDeslogar.setTextColor(getResources().getColor(R.color.branco_texto));
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