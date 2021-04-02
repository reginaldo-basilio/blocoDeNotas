package com.example.blocodenotas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blocodenotas.controller.NotaController;
import com.example.blocodenotas.modelo.Nota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNotas = findViewById(R.id.listViewNotas);
    }

    public void novaNota(View v){
        Intent intent = new Intent(this, exibirNota.class);
        intent.putExtra("idNota", 0);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NotaController notaController = new NotaController(this);
        ArrayList<Nota> arrayNotas = notaController.listaNotas();
        ArrayList<String> dataSet = notaController.listaStringNotas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataSet
        );
        listViewNotas.setAdapter(adapter);

        //CLICANDO EM UM ITEM DA LISTA PARA ABRIR NO FORM DE EDITAR NOTA
        listViewNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, editarNotas.class);
                String valor = String.valueOf(arrayNotas.get(position).getId());
                intent.putExtra("id", valor);
                intent.putExtra("titulo", arrayNotas.get(position).getTitulo());
                intent.putExtra("texto", arrayNotas.get(position).getTexto());
                startActivity(intent);
            }
        });

        //LONG CLICK PARA GERAR A PERGUNTA SE QUER DELETAR E DELETAR A NOTA
        listViewNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Tem certeza que quer deletar?");
                alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int valor = arrayNotas.get(position).getId();
                        String titulo = arrayNotas.get(position).getTitulo();
                        String texto = arrayNotas.get(position).getTexto();
                        Nota nota = new Nota(valor, titulo, texto);
                        Boolean check = notaController.excluirNota(nota);
                        if(check == true){
                            Toast.makeText(MainActivity.this, "Nota excluida", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "PROBLEMA", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = alert.create();
                ad.setTitle("Alerta");
                ad.show();
                return true ;
            }
        });
    }
}
