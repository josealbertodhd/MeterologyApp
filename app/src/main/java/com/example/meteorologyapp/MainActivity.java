package com.example.meteorologyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button b_aceptar;
    RadioButton rb_estacion1, rb_estacion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_aceptar = findViewById(R.id.b_aceptar);
        rb_estacion1 = findViewById(R.id.rb_estacion1);
        rb_estacion2 = findViewById(R.id.rb_estacion2);
        rb_estacion1.setChecked(true);


        b_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                if(rb_estacion1.isChecked()){
                    //Se manda la informacion acerca de la estacion elegida hacia el otro activity
                    intent.putExtra("estacion", "1.00");
                }else{
                    intent.putExtra("estacion", "2.00");
                }
                startActivity(intent);
            }
        });

    }
}