package app.connect.com.connect.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.connect.com.connect.ActivityContactProfile;
import app.connect.com.connect.Adapters.ContactsAdapter;
import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.R;
import app.connect.com.connect.RecyclerViewOnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {

    //RecyclerView redes sociais variaveis
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static FragmentSearch newInstance(String query) {
        FragmentSearch f = new FragmentSearch();
        Bundle args = new Bundle();
        args.putString("busca", query);
        f.setArguments(args);
        return f;
    }
    
    public FragmentSearch() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //String da busca
        String busca = "";
        getArguments().getString("busca", busca);

        //TODO teste
        //Abre o banco de dados
        PerfilDAO perfilDAO = new PerfilDAO(getContext());

        //Pega os perfis dos contatos armazenados no banco de dados
        //Correspondentes a busca
        final List<Perfil> perfilList = new ArrayList<>();

        //Verfica se a lista não está vazia
        if(perfilList.size() == 0){
            rootView.findViewById(R.id.tvPlaceholdermsg).setVisibility(View.VISIBLE);

        }


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_contacts);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
         mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
         mLayoutManager = new LinearLayoutManager(getContext());
         mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
         mAdapter = new ContactsAdapter(perfilList);
         mRecyclerView.setAdapter(mAdapter);

        mAdapter.setRecyclerViewOnClickListener(new RecyclerViewOnClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent intent = new Intent(getContext(), ActivityContactProfile.class);
                Bundle b = new Bundle();
                b.putSerializable("profile", perfilList.get(position)); //parameter
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }

            @Override
            public void onLongPressClickListener(View view, int position) {

            }
        });



        return rootView;
    }

}
