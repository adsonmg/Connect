package app.connect.com.connect;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import app.connect.com.connect.BancoDeDados.PerfilDAO;
import app.connect.com.connect.Fragments.FragmentEditProfile;
import app.connect.com.connect.Fragments.FragmentMain;
import app.connect.com.connect.Fragments.FragmentSearch;
import app.connect.com.connect.Objetos.Perfil;

public class MainActivity extends AppCompatActivity {

    //NavigationDrawer
    private  Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //************************************
        //Insere perfis se BD estiver vazio

        Perfil teste1 = new Perfil();
        teste1.setNome("joão");
        teste1.setEmail("joao.silva@outlook.com");
        teste1.setImage(R.mipmap.joao);
        Perfil teste2 = new Perfil();
        teste2.setNome("Manu");
        teste2.setEmail("manuzinha@outlook.com");
        teste2.setImage(R.mipmap.manu);
        Perfil teste3 = new Perfil();
        teste3.setNome("karoline");
        teste3.setEmail("karoline.htinha@gmail.com");
        teste3.setImage(R.mipmap.karoline);

        //Abre o banco de dados
        PerfilDAO perfilDAO = new PerfilDAO(this);
        if(perfilDAO.getPerfis(0).size() == 0){//Banco de dados vazio
            perfilDAO.inserirBD(teste1);
            perfilDAO.inserirBD(teste2);
            perfilDAO.inserirBD(teste3);
        }
        //*********************************************************************
        //*********************************************************************
        Log.i("Teste", String.valueOf(perfilDAO.getPerfis(1).size()));
        //Usuario ainda nao tem perfil
        if(perfilDAO.getPerfis(1).size() == 0
                || perfilDAO.getPerfis(1).get(0).getNome().equals("")
                || perfilDAO.getPerfis(1).get(0).getEmail().equals("")
                || perfilDAO.getPerfis(1).get(0).getTelefone().equals("")){

            new AlertDialog.Builder(this)
                    .setTitle("Crie seu perfil")
                    .setMessage("Para compartilhar suas informações de contanto, é necessário que você " +
                            "forneça alguns dados para o seu perfil.")
                    .setPositiveButton("Entendi", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            //Abre Fragment para edicao do perfil
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.fragment, new FragmentEditProfile());
                            ft.commit();
                        }}).show();
        }else {
            //Carrega o fragment main
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, new FragmentMain());
            ft.commit();
        }

        //Navigation Drawer
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(2).withName("Editar Perfil");
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        switch (position){
                            case 0:
                                ft.replace(R.id.fragment, new FragmentMain());
                                ft.commit();
                                break;
                            case 1:
                                ft.replace(R.id.fragment, new FragmentEditProfile());
                                ft.addToBackStack(null);
                                ft.commit();
                                break;
                        }
                        //Fecha o navigation drawer
                        navigationDrawerLeft.closeDrawer();
                        return true;
                    }
                })
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, new FragmentEditProfile());
                ft.addToBackStack(null);
                ft.commit();

        }
        */
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
