package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity {
    private Button buttonRegresar;
    private TextView textViewHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        buttonRegresar = findViewById(R.id.button_regresar);
        textViewHistorial = findViewById(R.id.txt_historial);

        buttonRegresar.setOnClickListener(view -> finish());

        ArrayList<Double> historial = (ArrayList<Double>) getIntent().getSerializableExtra("historial");

        if (historial != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Double resultado : historial) {
                stringBuilder.append(resultado).append("\n");
            }


            textViewHistorial.setText(stringBuilder.toString());
        } else {
            textViewHistorial.setText("No hay resultados.");
        }
    }
}
