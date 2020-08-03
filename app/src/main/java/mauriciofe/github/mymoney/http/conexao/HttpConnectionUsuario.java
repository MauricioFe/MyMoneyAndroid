package mauriciofe.github.mymoney.http.conexao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;
import mauriciofe.github.mymoney.models.Usuario;

public class HttpConnectionUsuario {
    public static String login(String uri) {
        Usuario usuario = new Usuario();
        usuario.setEmail("mauricio.lacerdaml@gmail.com");
        usuario.setSenha("86257765");
        String urlParameters = ParseUsuario.login(usuario);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
            //gerenciar o tráfego de rede. No sentido de consumir a rede.
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            writer.close();
            reader.close();
            Log.i("retornoApi", "PostDados: " + conn.getResponseCode());
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
