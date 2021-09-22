package com.example.projlolpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencia";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private Spinner spinner;
    private static final String[] SERVIDORES = {"BR", "EUNE", "EUW", "LAN", "LAS", "NA", "OCE", "RU", "TR", "JP", "KR", "SEA","CH"};

    String[] SERV_BR = {"Brasil"};
    String[] SERV_EUNE = {"Suécia", "Dinamarca", "Noruega", "Polônia"};
    String[] SERV_EUW = {"Inglaterra", "Alemanha", "França", "Reino Unido", "Portugal", "Espanha", "Países Baixos"};
    String[] SERV_LAN = {"México", "Panamá", "Cuba"};
    String[] SERV_LAS = {"Chile", "Argentina", "Bolívia"};
    String[] SERV_NA = {"Estados Unidos da América", "Canadá"};
    String[] SERV_OCE = {"Austrália", "Nova Zelândia"};
    String[] SERV_RU = {"Rússia"};
    String[] SERV_TR = {"Turquia"};
    String[] SERV_JP = {"Japão"};
    String[] SERV_KR = {"Coreia do Sul"};
    String[] SERV_SEA = {"Singapura", "Malásia", "Indonésia", "Filipinas"};
    String[] SERV_CH = {"China"};


    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinnerRegiao);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, SERVIDORES);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        if (preferences.contains("Nick") && preferences.contains("Regiao")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            getLocation();
            String pais = preferences.getString("Pais", "Algum");
            if (Arrays.toString(SERV_EUNE).contains(pais)){
                spinner.setSelection(1, true);
            } else if (Arrays.toString(SERV_EUW).contains(pais)){
                spinner.setSelection(2, true);
            } else if (Arrays.toString(SERV_LAN).contains(pais)){
                spinner.setSelection(3, true);
            } else if (Arrays.toString(SERV_LAS).contains(pais)){
                spinner.setSelection(4, true);
            } else if (Arrays.toString(SERV_NA).contains(pais)){
                spinner.setSelection(5, true);
            } else if (Arrays.toString(SERV_OCE).contains(pais)){
                spinner.setSelection(6, true);
            } else if (Arrays.toString(SERV_RU).contains(pais)){
                spinner.setSelection(7, true);
            } else if (Arrays.toString(SERV_TR).contains(pais)){
                spinner.setSelection(8, true);
            } else if (Arrays.toString(SERV_JP).contains(pais)){
                spinner.setSelection(9, true);
            } else if (Arrays.toString(SERV_KR).contains(pais)){
                spinner.setSelection(10, true);
            } else if (Arrays.toString(SERV_SEA).contains(pais)){
                spinner.setSelection(11, true);
            } else if (Arrays.toString(SERV_CH).contains(pais)){
                spinner.setSelection(12, true);
            } else { // Brasil
                spinner.setSelection(0, true);
            }

        }
    }

    public void abrirHomeActivity(View view) {
        EditText editNickname = (EditText) findViewById(R.id.editNickname);
        Spinner spinnerRegiao = (Spinner) findViewById(R.id.spinnerRegiao);

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (editNickname.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Preencha seu nickname!", Toast.LENGTH_LONG).show();
        } else {
            String nick = editNickname.getText().toString();
            String regiao = spinnerRegiao.getSelectedItem().toString();

            editor.putString("Nick", nick);
            editor.putString("Regiao", regiao);
            editor.putBoolean("Dark", false);
            editor.apply();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void getLocation() {
        // Testar permissão
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // Não permitido
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            // Permitido
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null){
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        try {
                            List<Address> addressList = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1
                            );
                            // Salvar país na SharedPreferences
                            String pais = addressList.get(0).getCountryName();

                            SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("Pais", pais);
                            editor.apply();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}