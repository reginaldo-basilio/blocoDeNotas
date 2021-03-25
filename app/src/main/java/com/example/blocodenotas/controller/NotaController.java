package com.example.blocodenotas.controller;

import android.content.Context;
import android.util.Log;

import com.example.blocodenotas.modelo.Nota;
import com.example.blocodenotas.modelo.NotaDao;

import java.util.ArrayList;

public class NotaController {
    Context mContext;
    NotaDao notaDao;
    public NotaController(Context c) {
        mContext = c;
        notaDao = new NotaDao(c);
    }

    public Nota cadastrarNovaNota(Nota n){
        return notaDao.inserirNota(n);
    }

    public Boolean atualizarNota(Nota n){
        return notaDao.updateNota(n);
    }

    public Boolean excluirNota(Nota n){
        return notaDao.deleteNota(n);
    }

    /*public Nota getNotaController(int idNota){
        return notaDao.getNota(idNota);
    }*/

    public ArrayList<Nota> listaNotas(){
        return notaDao.getListaNotas();
    }

    public ArrayList<String> listaStringNotas(){
        ArrayList<String> result = new ArrayList<String>();
        for(Nota nota : this.listaNotas()){
            result.add(nota.getTitulo());
        }
        return result;
    }
}
