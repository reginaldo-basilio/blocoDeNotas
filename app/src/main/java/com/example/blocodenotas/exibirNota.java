package com.example.blocodenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blocodenotas.controller.NotaController;
import com.example.blocodenotas.modelo.Nota;

import java.util.ArrayList;

public class exibirNota extends AppCompatActivity {
    NotaController notaController;
    EditText edTitulo, edTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_nota);
        notaController = new NotaController(getApplicationContext());
        edTitulo = findViewById(R.id.edTitulo);
        edTexto = findViewById(R.id.edTexto);
    }

    public void salvarNota(View v){
        Nota n = notaController.cadastrarNovaNota(new Nota(edTitulo.getText().toString(), edTexto.getText().toString()));
        edTitulo.setText("");
        edTexto.setText("");
    }
}