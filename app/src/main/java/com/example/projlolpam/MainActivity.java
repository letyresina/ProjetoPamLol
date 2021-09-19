package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";
    private Spinner spinner;
    private static final String[] servidores = {"BR", "EUNE", "EUW", "LAN", "LAS", "NA", "OCE", "RU", "TR", "JP", "KR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinnerRegiao);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,servidores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        if (preferences.contains("Nick") && preferences.contains("Regiao")){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else{

        }
    }

    public void abrirHomeActivity(View view) {
        EditText editNickname = (EditText) findViewById(R.id.editNickname);
        Spinner spinnerRegiao = (Spinner) findViewById(R.id.spinnerRegiao);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (editNickname.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Preencha seu nickname!", Toast.LENGTH_LONG).show();
        } else{
            String nick = editNickname.getText().toString();
            String regiao = spinnerRegiao.getSelectedItem().toString();

            editor.putString("Nick", nick);
            editor.putString("Regiao", regiao);
            editor.commit();
            
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}