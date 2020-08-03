package mauriciofe.github.mymoney.ui.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.Tasks.DeleteCategoria;
import mauriciofe.github.mymoney.Tasks.GetDadosCategoria;
import mauriciofe.github.mymoney.Tasks.PostCategoria;
import mauriciofe.github.mymoney.Tasks.PutCategoria;
import mauriciofe.github.mymoney.models.Categoria;

public class MainActivity extends AppCompatActivity {

    List<Categoria> categoriaList;
    static String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriaList = new ArrayList<>();
       // trustEveryone();
        //inserirDados("https://192.168.0.14:44325/api/categorias/");
        //editarDados("https://192.168.0.14:44325/api/categorias/22");
        // excluirDados("https://192.168.0.14:44325/api/categorias/22");
        //enviarLogin("https://192.168.0.14:44325/api/usuarios/login");

    }



    private void excluirDados(String uri) {
        if (isOnline()) {
            DeleteCategoria task = new DeleteCategoria(this);
            task.execute(uri);
        }
    }

    private void editarDados(String uri) {
        if (isOnline()) {
            PutCategoria task = new PutCategoria(this);
            task.execute(uri);
        } else {
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. Verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }

    private void inserirDados(String url) {
        if (isOnline()) {
            PostCategoria task = new PostCategoria(this);
            task.execute(url);
        } else {
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. Verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void buscaDados(String url, String token) {
        if (isOnline()) {
            GetDadosCategoria task = new GetDadosCategoria(this, categoriaList);
            task.execute(url, token);
        } else {
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }




}