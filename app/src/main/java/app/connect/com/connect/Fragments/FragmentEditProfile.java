package app.connect.com.connect.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEditProfile extends Fragment {

    EditText etNome;
    EditText etBio;
    EditText etEmail;
    EditText etTelefone;
    EditText etFacebook;
    EditText etInstagram;
    EditText etSnapchat;
    EditText etTwiiter;
    EditText etWhatsApp;

    public FragmentEditProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(false);

        //Instacia o banco de dados
        final PerfilDAO perfilDAO = new PerfilDAO(getContext());

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_edit_profile, container, false);

        etNome = (EditText) rootView.findViewById(R.id.etNome);
        etBio = (EditText) rootView.findViewById(R.id.etBio);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etTelefone = (EditText) rootView.findViewById(R.id.etTelefone);
        etFacebook = (EditText) rootView.findViewById(R.id.etFacebook);
        etInstagram = (EditText) rootView.findViewById(R.id.etInstagram);
        etSnapchat = (EditText) rootView.findViewById(R.id.etSnapchat);
        etTwiiter = (EditText) rootView.findViewById(R.id.etTwitter);
        etWhatsApp = (EditText) rootView.findViewById(R.id.etWhatsApp);

        FloatingActionButton btnSalvarPerfil = (FloatingActionButton) rootView.findViewById(R.id.btnSalvarPerfil);

        //Verifica se já existe um perfil do usuario cadastrado
        final List<Perfil> perfilUsuario = perfilDAO.getPerfis(1);

        final boolean usuarioExiste = perfilUsuario.size() != 0;

        //Se usuario existir, coloca seus dados nos textviews
        if(usuarioExiste){
            etNome.setText(perfilUsuario.get(0).getNome());
            etEmail.setText(perfilUsuario.get(0).getEmail());
            etBio.setText(perfilUsuario.get(0).getBio());
            etTelefone.setText(perfilUsuario.get(0).getTelefone());
            etFacebook.setText(perfilUsuario.get(0).getFacebook());
            etInstagram.setText(perfilUsuario.get(0).getInstagram());
            etSnapchat.setText(perfilUsuario.get(0).getSnapchat());
            etTwiiter.setText(perfilUsuario.get(0).getTwitter());
            etWhatsApp.setText(perfilUsuario.get(0).getWhatsApp());
        }

        btnSalvarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verfica campos obrigatorios
                if (etNome.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Preencha o campo obrigatório \"Nome\"", Toast.LENGTH_SHORT).show();
                } else if (etEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Preencha o campo obrigatório \"Email\"", Toast.LENGTH_SHORT).show();
                } else if (etTelefone.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Preencha o campo obrigatório \"Telefone\"", Toast.LENGTH_SHORT).show();
                } else {
                    if (!usuarioExiste) { //Perfil ainda nao existe. Deve ser criado
                        Perfil usuario = new Perfil(etNome.getText().toString(),
                                etEmail.getText().toString(),
                                etBio.getText().toString(),
                                etTelefone.getText().toString(),
                                1,
                                etFacebook.getText().toString(),
                                etInstagram.getText().toString(),
                                etSnapchat.getText().toString(),
                                etTwiiter.getText().toString(),
                                etWhatsApp.getText().toString()
                        );
                        //insere perfil no banco de dados
                        perfilDAO.inserirBD(usuario);
                    } else {//Apenas atualiza o perfil
                        Perfil usuario = new Perfil(
                                perfilUsuario.get(0).getId(),
                                etNome.getText().toString(),
                                etEmail.getText().toString(),
                                etBio.getText().toString(),
                                etTelefone.getText().toString(),
                                1,
                                etFacebook.getText().toString(),
                                etInstagram.getText().toString(),
                                etSnapchat.getText().toString(),
                                etTwiiter.getText().toString(),
                                etWhatsApp.getText().toString()
                        );
                        //atualiza perfil no banco de dados
                        perfilDAO.atualizarBD(usuario);
                    }

                    Toast.makeText(getContext(), "Perfil Atualizado", Toast.LENGTH_SHORT).show();

                    //Carrega o fragment main
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment, new FragmentMain());
                    ft.commit();
                }

            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
