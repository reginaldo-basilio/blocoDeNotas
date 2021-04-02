package com.example.blocodenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blocodenotas.controller.NotaController;
import com.example.blocodenotas.modelo.Nota;

public class editarNotas extends AppCompatActivity {
    NotaController notaController;
    EditText edTitulo, edTexto, edId;
    String id, titulo, texto;
    Button btnAtualizar;
    int idVlaue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notas);

        notaController = new NotaController(getApplicationContext());
        Intent intent = getIntent();

        id = (String) intent.getSerializableExtra("id");
        titulo = (String) intent.getSerializableExtra("titulo");
        texto = (String) intent.getSerializableExtra("texto");

        idVlaue = Integer.parseInt(id); //SETAR COMO INVISIVEL PARA NAO APARECER NO FORM

        edId = findViewById(R.id.edId2);
        edTitulo = findViewById(R.id.edTitulo2);
        edTexto = findViewById(R.id.edTexto2);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);

        edId.setText(id);
        edTitulo.setText(titulo);
        edTexto.setText(texto);
    }

    public void editarNota(View v){
        btnAtualizar.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Nota nota = new Nota(idVlaue , titulo, texto);
                    Boolean check = notaController.atualizarNota(nota);
                    if(check == true){
                        Toast.makeText(editarNotas.this, "Nota atualizada", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(editarNotas.this, "PROBLEMA", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );

    }
}