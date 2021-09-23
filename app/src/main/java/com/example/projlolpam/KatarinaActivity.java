package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class KatarinaActivity extends AppCompatActivity implements SensorEventListener {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";
    private static final String ARQUIVO_ANOTACOES = "notesKatarina.txt";
    private static final String KEY_ANOTACOES = "tempAnotacoesKatarina";

    SensorManager sensorManager;
    Sensor sensor;
    Float luminosidade;

    EditText editAnotacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katarina);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

         if (preferences.getBoolean("Automatico", false)) {
            if (preferences.getBoolean("Dark", false)){
                ativarDarkMode();
            }
        } else if (preferences.getBoolean("Dark", false)) {
            ativarDarkMode();
        }

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        editAnotacoes = findViewById(R.id.textUserNotes);

        if (preferences.contains(KEY_ANOTACOES)){
            editAnotacoes.setText(preferences.getString(KEY_ANOTACOES, ""));
            Toast.makeText(this, "savedinstance", Toast.LENGTH_SHORT).show();
        }
        else{
            carregarAnotacoes(editAnotacoes);
            Toast.makeText(this, "interno", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(KEY_ANOTACOES, editAnotacoes.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(KEY_ANOTACOES, editAnotacoes.getText().toString());
        editor.apply();

        sensorManager.unregisterListener(this);

        super.onPause();
    }

    public void salvarAnotacoes(View view){
        String anotacoes = editAnotacoes.getText().toString();
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(ARQUIVO_ANOTACOES, MODE_PRIVATE);
            fileOutputStream.write(anotacoes.getBytes());
            Toast.makeText(this, "Anotações Salvas!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void carregarAnotacoes(View view){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(ARQUIVO_ANOTACOES);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String anotacoes;

            while ((anotacoes = bufferedReader.readLine()) != null){
                stringBuilder.append(anotacoes).append("\n");
            }

            editAnotacoes.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        finish();
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

                    Intent intent = new Intent(KatarinaActivity.this, KatarinaActivity.class);
                    startActivity(intent);
                    this.overridePendingTransition(0, 0);
                    finish();
                } else if (preferences.getBoolean("Automatico", false) && luminosidade >= 20000) {
                    editor.putBoolean("Dark", false).apply();

                    Intent intent = new Intent(KatarinaActivity.this, KatarinaActivity.class);
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

    public void ativarDarkMode(){
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
        LinearLayout linearDivisao3 = (LinearLayout) findViewById(R.id.linearDivisao3);
        LinearLayout linearDivisao4 = (LinearLayout) findViewById(R.id.linearDivisao4);
        TextView textAnotacoes = (TextView) findViewById(R.id.textAnotacoes);
        EditText editAnotacoes = (EditText) findViewById(R.id.textUserNotes);
        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);

        linearCabecalho.setBackgroundResource(R.color.preto_cabecalho);
        imgbtnCampeoes.setImageResource(R.drawable.imgcampeoes);
        imgbtnItens.setImageResource(R.drawable.imgitens);
        imgbtnPerfil.setImageResource(R.drawable.imgperfil);
        imgBackground.setImageResource(R.drawable.imggwen_fundo);
        imgBackground.setScrollX(-240);
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
        linearDivisao4.setBackgroundResource(R.color.branco_topocontainer);
        textAnotacoes.setTextColor(getResources().getColor(R.color.branco_tit_borda));
        editAnotacoes.setTextColor(getResources().getColor(R.color.branco_texto));
        editAnotacoes.getBackground().mutate().setColorFilter(getResources().getColor(R.color.branco_tit_borda), PorterDuff.Mode.SRC_ATOP);
        buttonSalvar.setTextColor(getResources().getColor(R.color.branco_texto));
    }
}