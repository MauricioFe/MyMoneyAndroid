package mauriciofe.github.mymoney;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import javax.sql.ConnectionEvent;

import mauriciofe.github.mymoney.http.conexao.HttpConnection;
import mauriciofe.github.mymoney.http.parseJson.ParseCategoria;
import mauriciofe.github.mymoney.models.Categoria;

public class MainActivity extends AppCompatActivity {

    List<Categoria> categoriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriaList = new ArrayList<>();
        trustEveryone();
        buscaDados("https://192.168.0.8:44387/api/Categoria");
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void buscaDados(String url) {
        if (isOnline()) {
            MyTask task = new MyTask();
            task.execute(url);
        }else{
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String content = HttpConnection.getDados(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String conteudo) {
            categoriaList = ParseCategoria.getCategoriasJson(conteudo);
            if (categoriaList != null) {
                for (Categoria item : categoriaList) {
                    Log.i("Return API", "Id da categoria " + item.getId() + "\n" + "Descrição " + item.getDescricao() + "\n");
                }
            }
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