package mauriciofe.github.mymoney.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.movimentacao.GetMovimentacoes;
import mauriciofe.github.mymoney.ui.activities.fragments.movimentacoes.MovimentacaoFragment;
import mauriciofe.github.mymoney.ui.activities.login.LoginActivity;


public class MenuActivity extends AppCompatActivity  {

    Usuario usuario;
    String token;
    private AppBarConfiguration mAppBarConfiguration;
    TextView txtNome;
    TextView txtEmail;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CadastrarMovimentacaoActivity.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("token", token);
                startActivity(intent);

            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movimentacao, R.id.nav_simulacao, R.id.nav_relatorios)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        Intent extras = getIntent();
        txtNome = headerView.findViewById(R.id.nav_header_txtNome);
        txtEmail = headerView.findViewById(R.id.nav_header_txtEmail);
        if (extras.hasExtra("usuario") && extras.hasExtra("token")) {
            usuario = (Usuario) extras.getSerializableExtra("usuario");
            token = extras.getStringExtra("token");
            if (usuario != null) {
                txtNome.setText(usuario.getNome());
                txtEmail.setText(usuario.getEmail());
            }
        }
//        Bundle bundle = new Bundle();
//        bundle.putString(token, "token");
//        MovimentacaoFragment fragment = new MovimentacaoFragment();
//        fragment.setArguments(bundle);
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
        Bundle bundle = new Bundle();
        String myMessage = "Stackoverflow is cool!";
        bundle.putString("message", myMessage );
        MovimentacaoFragment fragInfo = new MovimentacaoFragment();
        fragInfo.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragInfo);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}