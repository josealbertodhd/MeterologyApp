package com.example.meteorologyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    Button b_aceptar3;
    RadioButton rb_grafico, rb_tabla;
    private String estacionA3;
    private String variableA3, fecha1, fecha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        b_aceptar3 = findViewById(R.id.b_aceptar3);
        rb_grafico = findViewById(R.id.rb_grafico);
        rb_tabla = findViewById(R.id.rb_tabla);
        rb_tabla.setChecked(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            estacionA3 = bundle.getString("estacion2");
            variableA3 = bundle.getString("variable");
            fecha1 = bundle.getString("fecha1");
            fecha2 = bundle.getString("fecha2");
            Toast.makeText(this, "Estacion : " + estacionA3 + ", Variable: " + variableA3,
                    Toast.LENGTH_SHORT).show();
        }

        b_aceptar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rb_tabla.isChecked()){
                    Intent intent = new Intent(Activity3.this, ActivityTabla.class);
                    intent.putExtra("estacion3", estacionA3);
                    intent.putExtra("variable3", variableA3);
                    intent.putExtra("fecha1A", fecha1);
                    intent.putExtra("fecha2A", fecha2);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Activity3.this, ActivityGrafico.class);
                    intent.putExtra("estacion3", estacionA3);
                    intent.putExtra("variable3", variableA3);
                    intent.putExtra("fecha1A", fecha1);
                    intent.putExtra("fecha2A", fecha2);
                    startActivity(intent);
                }
            }
        });
    }
}