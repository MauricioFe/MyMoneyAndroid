package mauriciofe.github.mymoney;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

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

import mauriciofe.github.mymoney.Tasks.GetDadosCategoria;
import mauriciofe.github.mymoney.models.Categoria;

public class MainActivity extends AppCompatActivity {

    List<Categoria> categoriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriaList = new ArrayList<>();
        trustEveryone();
        buscaDados("https://192.168.0.10:44387/api/Categoria");
        inserirDados("https://192.168.0.10:44387/api/Categoria");
    }

    private void inserirDados(String url) {
        if (isOnline()){

        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void buscaDados(String url) {
        if (isOnline()) {
            GetDadosCategoria task = new GetDadosCategoria(this,categoriaList);
            task.execute(url);
        }else{
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }



    /*Código para fazer conexão com urls e ssls não seguros*/
    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

}