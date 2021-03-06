package mauriciofe.github.mymoney.http.conexao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionTipoMovimentacao {
    public static String getDados(String uri, String token){
        BufferedReader reader;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", token);
            conn.setDoOutput(false);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine())!= null){
                stringBuilder.append(line);
            }
            Log.i("response server", conn.getResponseMessage());
            return stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
