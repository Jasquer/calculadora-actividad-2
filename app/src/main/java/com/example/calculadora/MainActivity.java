package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // botones
    private Button btn_suma;
    private Button btn_resta;
    private Button btn_division;
    private Button btn_multiplicacion;
    private Button btn_historial;
    private Button btn_borrar; // Botón para borrar
    // respuesta
    private TextView text_respuesta;

    // numeros
    private EditText edit_numero_1;
    private EditText edit_numero_2;

    // la lista
    private ArrayList<Double> listaResultados;

    // Resultado acumulado
    private double resultadoAcumulado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_respuesta = findViewById(R.id.txt_respuesta);
        edit_numero_1 = findViewById(R.id.txt_numero1);
        edit_numero_2 = findViewById(R.id.txt_numero2);

        listaResultados = new ArrayList<>();
        resultadoAcumulado = 0; // Inicializa el resultado acumulado

        // Suma
        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(view -> {
            if (validarCampos()) {
                double numero = obtenerNumero2(); // Obtén el segundo número
                double resultado = suma(resultadoAcumulado, numero); // Usa el resultado acumulado
                resultadoAcumulado = resultado; // Actualiza el resultado acumulado
                text_respuesta.setText(formatearResultado(resultado));
                listaResultados.add(resultado);
            }
        });

        // Resta
        btn_resta = findViewById(R.id.button_restar);
        btn_resta.setOnClickListener(view -> {
            if (validarCampos()) {
                double numero = obtenerNumero2(); // Obtén el segundo número
                double resultado = resta(resultadoAcumulado, numero); // Usa el resultado acumulado
                resultadoAcumulado = resultado; // Actualiza el resultado acumulado
                text_respuesta.setText(formatearResultado(resultado));
                listaResultados.add(resultado);
            }
        });

        // División
        btn_division = findViewById(R.id.button_dividir);
        btn_division.setOnClickListener(view -> {
            if (validarCampos()) {
                double divisor = obtenerNumero2();
                if (divisor == 0) {
                    Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                } else {
                    double resultado = division(resultadoAcumulado, divisor); // Usa el resultado acumulado
                    resultadoAcumulado = resultado; // Actualiza el resultado acumulado
                    text_respuesta.setText(formatearResultado(resultado));
                    listaResultados.add(resultado);
                }
            }
        });

        // Multiplicación
        btn_multiplicacion = findViewById(R.id.button_multiplicar);
        btn_multiplicacion.setOnClickListener(view -> {
            if (validarCampos()) {
                double numero = obtenerNumero2(); // Obtén el segundo número
                double resultado = multiplicacion(resultadoAcumulado, numero); // Usa el resultado acumulado
                resultadoAcumulado = resultado; // Actualiza el resultado acumulado
                text_respuesta.setText(formatearResultado(resultado));
                listaResultados.add(resultado);
            }
        });

        // Botón historial
        btn_historial = findViewById(R.id.button_historial);
        btn_historial.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
            intent.putExtra("historial", listaResultados);
            startActivity(intent);
        });

        // Botón borrar
        btn_borrar = findViewById(R.id.button_borrar);
        btn_borrar.setOnClickListener(view -> {
            resultadoAcumulado = 0; // Restablece el resultado acumulado
            text_respuesta.setText(""); // Limpia el TextView
            // Opcional: Limpiar campos de entrada si es necesario
            edit_numero_1.setText("");
            edit_numero_2.setText("");
        });
    }

    public double suma(double a, double b) {
        return a + b;
    }

    public double resta(double a, double b) {
        return a - b;
    }

    public double multiplicacion(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        return a / b;
    }

    private boolean validarCampos() {
        if (edit_numero_1.getText().toString().isEmpty() || edit_numero_2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa ambos números", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private double obtenerNumero2() {
        try {
            return Double.parseDouble(edit_numero_2.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingresa un número válido en el segundo campo", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    // Método para formatear el resultado
    private String formatearResultado(double resultado) {
        // Si el resultado es entero, muéstralo sin decimales
        if (resultado % 1 == 0) {
            return String.format("%d", (int) resultado);
        } else {
            // Si no es entero, muéstralo con una cantidad fija de decimales
            DecimalFormat df = new DecimalFormat("0.##");
            return df.format(resultado);
        }
    }
}
