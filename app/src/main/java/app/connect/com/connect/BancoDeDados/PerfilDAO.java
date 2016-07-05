package app.connect.com.connect.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.Objetos.RedeSocial;

/**
 * Created by aluno on 28/06/16.
 */

public class PerfilDAO {

    private SQLiteDatabase bancoDeDados;

    public PerfilDAO(Context context){
        BDcore auxBD = new BDcore(context);
        bancoDeDados = auxBD.getWritableDatabase();
    }

    //Insere dados no BD
    public void inserirBD(Perfil perfil){
        ContentValues valores = new ContentValues();
        valores.put("nome", perfil.getNome());
        valores.put("bio", perfil.getEmail());
        valores.put("email", perfil.getEmail());
        valores.put("telefone", perfil.getTelefone());
        valores.put("tipo", perfil.getTipo());
        valores.put("facebook", perfil.getFacebook());
        valores.put("instagram", perfil.getInstagram());
        valores.put("snapchat", perfil.getSnapchat());
        valores.put("twitter", perfil.getTwitter());
        valores.put("whatsapp", perfil.getWhatsApp());
        valores.put("image", perfil.getImage());
        bancoDeDados.insert("perfis", null, valores);
    }


    //Atualiza valores ja salvos no BD
    public void atualizarBD(Perfil perfil){
        ContentValues valores = new ContentValues();
        valores.put("nome", perfil.getNome());
        valores.put("bio", perfil.getEmail());
        valores.put("email", perfil.getEmail());
        valores.put("telefone", perfil.getTelefone());
        valores.put("tipo", perfil.getTipo());
        valores.put("facebook", perfil.getFacebook());
        valores.put("instagram", perfil.getInstagram());
        valores.put("snapchat", perfil.getSnapchat());
        valores.put("twitter", perfil.getTwitter());
        valores.put("whatsapp", perfil.getWhatsApp());
        valores.put("image", perfil.getImage());
        bancoDeDados.update("perfis", valores, "_id = '" + perfil.getId() + "'", null);
    }

    //Retorna valores do BD
    public List<Perfil> getPerfis(int tipo){
        List<Perfil> perfis = new ArrayList<>();

        String[] colunas = new String[]{"_id", "nome", "bio",
            "email", "telefone", "tipo",
            "facebook", "instagram", "snapchat",
            "twitter", "whatsapp", "image"};

        Cursor cursor = bancoDeDados.query("perfis", colunas, null, null, null, null, "_id DESC");

        if(cursor.getCount() > 0){
            //verifica se tem algo salvo no BD
            //movimenta cursor para o inicio do BD
            cursor.moveToFirst();

            do{
                if(cursor.getInt(5) == tipo) {
                    Perfil perfil = new Perfil();
                    perfil.setId(cursor.getInt(0));
                    perfil.setNome(cursor.getString(1));
                    perfil.setBio(cursor.getString(2));
                    perfil.setEmail(cursor.getString(3));
                    perfil.setTelefone(cursor.getString(4));
                    perfil.setTipo(cursor.getInt(5));
                    perfil.setFacebook(cursor.getString(6));
                    perfil.setInstagram(cursor.getString(7));
                    perfil.setSnapchat(cursor.getString(8));
                    perfil.setTwitter(cursor.getString(9));
                    perfil.setWhatsApp(cursor.getString(10));
                    perfil.setImage(cursor.getInt(11));
                    perfis.add(perfil);
                }
            }while (cursor.moveToNext());
        }

        return perfis;
    }

    public List<Perfil> busca(int tipo, String busca){
        List<Perfil> perfis = new ArrayList<>();

        String[] colunas = new String[]{"_id", "nome", "bio",
                "email", "telefone", "tipo",
                "facebook", "instagram", "snapchat",
                "twitter", "whatsapp", "image"};

        Cursor cursor = bancoDeDados.query("perfis", colunas, null, null, null, null, "_id DESC");
        Log.i("busca", busca.toLowerCase());


        if(cursor.getCount() > 0){
            //verifica se tem algo salvo no BD
            //movimenta cursor para o inicio do BD
            cursor.moveToFirst();
            do{
                String nome = cursor.getString(1);
                if(cursor.getInt(5) == tipo) {
                    Perfil perfil = new Perfil();
                    perfil.setId(cursor.getInt(0));
                    perfil.setNome(cursor.getString(1));
                    perfil.setBio(cursor.getString(2));
                    perfil.setEmail(cursor.getString(3));
                    perfil.setTelefone(cursor.getString(4));
                    perfil.setTipo(cursor.getInt(5));
                    perfil.setFacebook(cursor.getString(6));
                    perfil.setInstagram(cursor.getString(7));
                    perfil.setSnapchat(cursor.getString(8));
                    perfil.setTwitter(cursor.getString(9));
                    perfil.setWhatsApp(cursor.getString(10));
                    perfil.setImage(cursor.getInt(11));
                    if(perfil.getNome().toLowerCase().contains(busca.toLowerCase())) {
                        perfis.add(perfil);
                    }
                }
            }while (cursor.moveToNext());
        }

        return perfis;
    }

}
