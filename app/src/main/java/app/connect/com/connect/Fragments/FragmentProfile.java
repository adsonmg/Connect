package app.connect.com.connect.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentProfile extends Fragment {

    TextView tvNome;
    TextView tvBio;
    TextView tvEmail;
    TextView tvTelefone;
    TextView tvFacebook;
    TextView tvInstagram;
    TextView tvSnapchat;
    TextView tvTwiiter;
    TextView tvWhatsApp;

    //Banco de dados redes sociais variavel
    //private RedeSocialDAO redeSocialDAO;


    public FragmentProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instacia o banco de dados
        final PerfilDAO perfilDAO = new PerfilDAO(getContext());

        View rootView;
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvNome = (TextView) rootView.findViewById(R.id.tvNome);
        tvBio = (TextView) rootView.findViewById(R.id.tvBio);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvTelefone = (TextView) rootView.findViewById(R.id.tvTelefone);
        tvFacebook = (TextView) rootView.findViewById(R.id.tvFacebook);
        tvInstagram = (TextView) rootView.findViewById(R.id.tvInstagram);
        tvSnapchat = (TextView) rootView.findViewById(R.id.tvSnapchat);
        tvTwiiter = (TextView) rootView.findViewById(R.id.tvTwitter);
        tvWhatsApp = (TextView) rootView.findViewById(R.id.tvWhatsApp);


        GridLayout glFacebook = (GridLayout) rootView.findViewById(R.id.glFacebook);
        GridLayout glInstagram = (GridLayout) rootView.findViewById(R.id.glInstagram);
        GridLayout glSnapchat = (GridLayout) rootView.findViewById(R.id.glSnapchat);
        GridLayout glTwiiter =  (GridLayout) rootView.findViewById(R.id.glTwitter);
        GridLayout glWhatsApp = (GridLayout) rootView.findViewById(R.id.glWhatsApp);

        //Verifica se já existe um perfil do usuario cadastrado
        final List<Perfil> perfilUsuario = perfilDAO.getPerfis(1);

        final boolean usuarioExiste = perfilUsuario.size() != 0;

        //Se usuario existir, coloca seus dados nos textviews
        if(usuarioExiste){

            tvNome.setText(perfilUsuario.get(0).getNome());
            tvEmail.setText(perfilUsuario.get(0).getEmail());
            tvBio.setText(perfilUsuario.get(0).getBio());
            tvTelefone.setText(perfilUsuario.get(0).getTelefone());
            if(!perfilUsuario.get(0).getFacebook().equals("")) {
                tvFacebook.setText(perfilUsuario.get(0).getFacebook());
            }else{
                glFacebook.setVisibility(View.GONE);
            }
            if(!perfilUsuario.get(0).getInstagram().equals("")) {
                tvInstagram.setText(perfilUsuario.get(0).getInstagram());
            }else{
                glInstagram.setVisibility(View.GONE);
            }
            if(!perfilUsuario.get(0).getSnapchat().equals("")) {
                tvSnapchat.setText(perfilUsuario.get(0).getSnapchat());
            }else{
                glSnapchat.setVisibility(View.GONE);
            }
            if(!perfilUsuario.get(0).getTwitter().equals("")) {
                tvTwiiter.setText(perfilUsuario.get(0).getTwitter());
            }else{
                glTwiiter.setVisibility(View.GONE);
            }
            if(!perfilUsuario.get(0).getWhatsApp().equals("")) {
                tvWhatsApp.setText(perfilUsuario.get(0).getWhatsApp());
            }else{
                glWhatsApp.setVisibility(View.GONE);
            }

        }


        return rootView;
    }
}
