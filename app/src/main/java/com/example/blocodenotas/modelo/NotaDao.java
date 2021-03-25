package com.example.blocodenotas.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static java.sql.Types.INTEGER;

public class NotaDao {
    SQLiteDatabase dataBase;

    public NotaDao(Context c) {
        dataBase = c.openOrCreateDatabase("dbNotas", c.MODE_PRIVATE, null);
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                          "titulo varchar,"+
                                                          "texto varchar);");
    }

    public Nota inserirNota(Nota n){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("texto", n.getTexto());
        int i = (int) dataBase.insert("notas", null, contentValues);
        n. setId(i);
        return n;
    }

    public ArrayList<Nota> getListaNotas(){
        Cursor cursor = dataBase.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst();
        ArrayList<Nota> listaNotas = new ArrayList<Nota>();
        while(!cursor.isAfterLast()){
            Nota n = new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            listaNotas.add(n);
            cursor.moveToNext();
        }
       /* while(!cursor.isAfterLast()){
            listaNotas.add(new Nota(cursor.getInt(cursor.getColumnIndex("id")),
                                    cursor.getString(cursor.getColumnIndex("titulo")),
                                    cursor.getString(cursor.getColumnIndex ("texto"))));
            cursor.moveToNext();
        }*/
        return listaNotas;
    }

    public Boolean updateNota(Nota n){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", n.getId());
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("texto", n.getTexto());
        int i = dataBase.update("notas", contentValues, "id = ?", new String[]{""+n.getId()});
        if(i>0) {
            return true;
        }else{
            return false;
        }
    }

    public Boolean deleteNota(Nota n){;
        int i = dataBase.delete("notas", "id = ?", new String[]{""+n.getId()});
        if(i>0) {
            return true;
        }else{
            return false;
        }
    }

    /*public Nota getNota(int idNota){
        Cursor cursor = dataBase.rawQuery("SELECT * FROM notas WHERE id = idNota", null);
        cursor.moveToFirst();
        Nota n = new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        //cursor.moveToNext();
        return n;
    }*/

}
