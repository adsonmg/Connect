package app.connect.com.connect;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.Objetos.Perfil;

public class ActivityContactProfile extends AppCompatActivity {

    TextView tvNome;
    TextView tvBio;
    TextView tvEmail;
    TextView tvTelefone;
    TextView tvFacebook;
    TextView tvInstagram;
    TextView tvSnapchat;
    TextView tvTwiiter;
    TextView tvWhatsApp;
    ImageView imgProfile;
    Perfil perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        //Get parameter
        Bundle b = getIntent().getExtras();
        perfil = null; // or other values
        if(b != null) {
            perfil = (Perfil) b.getSerializable("profile");

            tvNome = (TextView) findViewById(R.id.tvNome);
            tvBio = (TextView) findViewById(R.id.tvBio);
            tvEmail = (TextView) findViewById(R.id.tvEmail);
            tvTelefone = (TextView) findViewById(R.id.tvTelefone);
            tvFacebook = (TextView) findViewById(R.id.tvFacebook);
            tvInstagram = (TextView) findViewById(R.id.tvInstagram);
            tvSnapchat = (TextView) findViewById(R.id.tvSnapchat);
            tvTwiiter = (TextView) findViewById(R.id.tvTwitter);
            tvWhatsApp = (TextView) findViewById(R.id.tvWhatsApp);
            imgProfile = (ImageView) findViewById(R.id.imgProfile);


            GridLayout glFacebook = (GridLayout) findViewById(R.id.glFacebook);
            GridLayout glInstagram = (GridLayout) findViewById(R.id.glInstagram);
            GridLayout glSnapchat = (GridLayout) findViewById(R.id.glSnapchat);
            GridLayout glTwiiter = (GridLayout) findViewById(R.id.glTwitter);
            GridLayout glWhatsApp = (GridLayout) findViewById(R.id.glWhatsApp);


            tvNome.setText(perfil.getNome());
            tvEmail.setText(perfil.getEmail());
            tvBio.setText(perfil.getBio());
            tvTelefone.setText(perfil.getTelefone());
            imgProfile.setImageResource(perfil.getImage());


            /*
            if (!perfil.getFacebook().equals("")) {
                tvFacebook.setText(perfil.getFacebook());
            } else {
                glFacebook.setVisibility(View.GONE);
            }
            if (!perfil.getInstagram().equals("")) {
                tvInstagram.setText(perfil.getInstagram());
            } else {
                glInstagram.setVisibility(View.GONE);
            }
            if (!perfil.getSnapchat().equals("")) {
                tvSnapchat.setText(perfil.getSnapchat());
            } else {
                glSnapchat.setVisibility(View.GONE);
            }
            if (!perfil.getTwitter().equals("")) {
                tvTwiiter.setText(perfil.getTwitter());
            } else {
                glTwiiter.setVisibility(View.GONE);
            }
            if (!perfil.getWhatsApp().equals("")) {
                tvWhatsApp.setText(perfil.getWhatsApp());
            } else {
                glWhatsApp.setVisibility(View.GONE);
            }
            */
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        MenuItem m1 = menu.add(0, 0, 0, "Excluir Contato");
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        new AlertDialog.Builder(this)
                .setTitle("Excluir perfil")
                .setMessage("Você deseja excluir este contato permanentemente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        PerfilDAO perfilDAO = new PerfilDAO(ActivityContactProfile.this);
                        perfilDAO.excluirBD(perfil);
                        ActivityContactProfile.this.finish();
                        Toast.makeText(ActivityContactProfile.this, "Perfil Excluido", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton("Não", null).show();

        return super.onOptionsItemSelected(item);
    }
}
