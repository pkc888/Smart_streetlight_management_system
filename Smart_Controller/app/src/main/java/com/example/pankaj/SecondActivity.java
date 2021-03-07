package com.example.pankaj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btnManually, btnAutomatically;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnManually = (Button) findViewById(R.id.btn_manually);
        btnManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });
        btnAutomatically = (Button) findViewById(R.id.btn_auto);

        btnAutomatically.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, AutomaticActivity.class);
                startActivity(intent);
            }
        });
    }
}