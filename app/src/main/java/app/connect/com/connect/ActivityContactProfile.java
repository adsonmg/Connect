package app.connect.com.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.connect.com.connect.Objetos.Perfil;

public class ActivityContactProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        //Get parameter
        Bundle b = getIntent().getExtras();
        Perfil perfil = null; // or other values
        if(b != null)
            perfil = (Perfil) b.getSerializable("profile");

        Toast.makeText(this, perfil.getNome(), Toast.LENGTH_LONG).show();

    }
}
