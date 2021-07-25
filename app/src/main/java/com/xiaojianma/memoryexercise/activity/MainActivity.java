package com.xiaojianma.memoryexercise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaojianma.memoryexercise.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button numberBtn = findViewById(R.id.number_btn);
        Button letterBtn = findViewById(R.id.letter_btn);
        numberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NumberCodeActivity.class);
                startActivity(intent);
            }
        });
        letterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LetterCodeActivity.class);
                startActivity(intent);
            }
        });
    }
}