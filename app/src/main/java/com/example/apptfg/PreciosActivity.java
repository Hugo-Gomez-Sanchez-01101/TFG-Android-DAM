package com.example.apptfg;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.apptfg.algoritmo.Generador;
import com.example.apptfg.regla.Usos;

public class PreciosActivity extends FatherView {
    private SeekBar seekBarMinimo;
    private SeekBar seekBarMaximo;
    private TextView textViewMinimo;
    private TextView textViewMaximo;
    int current;
    Enum<Usos> uso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precios);
        darFuncionalidadMenu();
        setup();
        definirValores();
    }

    private void definirValores() {

    }

    private void setup() {
        Bundle bundle = getIntent().getExtras();
        int minimo = bundle.getInt("minimo");
        int maximo = bundle.getInt("maximo");
        current = minimo;
        findViewById(R.id.btnGenerarOrdenador).setOnClickListener(V -> generarOrdenador());
        findViewById(R.id.buttonVolver).setOnClickListener(v -> volver());
        textViewMinimo = findViewById(R.id.textViewMinimo);
        textViewMaximo = findViewById(R.id.textViewMaximo);
        seekBarMinimo = findViewById(R.id.seekBarMinimo);
        seekBarMaximo = findViewById(R.id.seekBarMaxmio);
        uso = (Usos) bundle.getSerializable("uso");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBarMinimo.setMax((maximo - 1) - minimo);
            seekBarMinimo.setProgress(current - minimo);
            textViewMinimo.setText(current + "€");

            seekBarMaximo.setMax(maximo - minimo);
            seekBarMaximo.setProgress((minimo + 1)- minimo);
            textViewMaximo.setText(current + "€");
        }

        seekBarMinimo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + minimo;
                textViewMinimo.setText(current + "€");
                int max = Integer.parseInt(textViewMaximo.getText().toString().split("€")[0]);
                if (current >= max) {
                    textViewMaximo.setText(current + 1 + "€");
                    seekBarMaximo.setProgress((current + 1) - minimo);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBarMaximo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + minimo;
                textViewMaximo.setText(current + "€");
                int min = Integer.parseInt(textViewMinimo.getText().toString().split("€")[0]);
                if (current <= min) {
                    textViewMinimo.setText(current - 1 + "€");
                    seekBarMinimo.setProgress((current - 1) - minimo);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void volver() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    private void generarOrdenador() {
        int minimo = Integer.parseInt(textViewMinimo.getText().toString().split("€")[0]);
        int maximo = Integer.parseInt(textViewMaximo.getText().toString().split("€")[0]);
        Generador generador = new Generador(uso, minimo, maximo);
    }
}