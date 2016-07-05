package app.connect.com.connect.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.connect.com.connect.Adapters.ContactsAdapter;
import app.connect.com.connect.Adapters.RequestAdapter;
import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.MainActivity;
import app.connect.com.connect.Objetos.Perfil;
import app.connect.com.connect.R;
import app.connect.com.connect.RecyclerViewOnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRequest extends Fragment {

    //RecyclerView redes sociais variaveis
    private RecyclerView mRecyclerView;
    private RequestAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Banco de dados
    PerfilDAO perfilDAO;


    public FragmentRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);
        perfilDAO = new PerfilDAO(getContext());


        //TODO teste

        final List<Perfil> snList = new ArrayList<>();
        Perfil teste1 = new Perfil();
        teste1.setNome("gabrielle");
        teste1.setTipo(0);
        teste1.setEmail("gabrielle@email.com");
        teste1.setImage(R.mipmap.gabrielle);
        Perfil teste2 = new Perfil();
        teste2.setNome("barbara");
        teste2.setTipo(0);
        teste2.setEmail("barbara@email.com");
        teste2.setImage(R.mipmap.barbara);
        Perfil teste3 = new Perfil();
        teste3.setNome("bruna");
        teste3.setTipo(0);
        teste3.setEmail("bruna@email.com");
        teste3.setImage(R.mipmap.bruna);

        snList.add(teste1);
        snList.add(teste2);
        snList.add(teste3);

        List<Perfil> perfisBD = perfilDAO.getPerfis(0);
        final List<Perfil> dataPerfilSet = new ArrayList<>();
        boolean existe;
        /*Retira atuais contatos da lista*/
        for(int i = 0; i < snList.size(); i++){
            existe = false;
           for(int j = 0; j < perfisBD.size(); j++){
               //verifica se perfil esta no BD
               if(snList.get(i).getNome().equals(perfisBD.get(j).getNome())){
                   existe = true;
               }
           }
            if(!existe){
                dataPerfilSet.add(snList.get(i));
            }
        }

        //Verfica se a lista não está vazia
        if(dataPerfilSet.size() == 0){
            rootView.findViewById(R.id.tvPlaceholdermsg).setVisibility(View.VISIBLE);

        }

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_request);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RequestAdapter(dataPerfilSet);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setRecyclerViewOnClickListener(new RecyclerViewOnClickListener() {
            @Override
            public void onClickListener(View view, final int position) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Enviar solicitação")
                        .setMessage("Você deseja solicitar as informações de contato de " + dataPerfilSet.get(position).getNome() + "?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //Simula solicitação
                                aceitarSolicitacao(dataPerfilSet.get(position), dataPerfilSet, position);
                                Toast.makeText(getContext(), "Solicitação enviada", Toast.LENGTH_LONG).show();
                            }})
                        .setNegativeButton("Não", null).show();
            }

            @Override
            public void onLongPressClickListener(View view, int position) {

            }
        });


        //Cria uma thread para simular que está procurando usuários ao redor
        final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Procurando", "Aguarde..",
                true, false);
        ringProgressDialog.setIndeterminate(true);
        new Thread(new Runnable() {
            public void run() {
                try {


                    Thread.sleep(3000);


                    if (ringProgressDialog != null && ringProgressDialog.isShowing()) {
                        ringProgressDialog.dismiss();
                    }



                } catch (Exception e) {

                }

            }

        }).start();
        return rootView;
    }

    void aceitarSolicitacao(final Perfil perfil, final List<Perfil> snList, final int position){
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                new AlertDialog.Builder(getContext())
                        .setTitle("Solicitação Aceita!")
                        .setMessage(snList.get(position).getNome() + " agora faz parte da sua lista de contatos")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }})
                        .show();

                //Insere no banco de dados
                perfilDAO.inserirBD(perfil);
                //Remove da lista
                snList.remove(position);
                mAdapter.notifyDataSetChanged();

            }

        }.start();

    }

}
