package app.connect.com.connect.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by aluno on 28/06/16.
 */
public class BDcore extends SQLiteOpenHelper {
    //Nome do banco de dados
    private static final String NOME_BD = "CONNECT_BD";
    private static final int VERSAO_BD = 11;

    public BDcore(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table perfis (_id integer primary key autoincrement," +
                "nome text not null," +
                "bio text," +
                "email text," +
                "telefone text," +
                "tipo int not null," +
                "facebook text," +
                "instagram text," +
                "snapchat text," +
                "twitter text," +
                "whatsapp text," +
                "image integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Apaga tabela antiga
        db.execSQL("drop table perfis");
        //cria uma nova tabela
        onCreate(db);
    }
}
