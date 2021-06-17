package com.example.projlolpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NICKNAME);

        // Cria um elemento text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // define o text view com layout ativo
        setContentView(textView);
    }
}