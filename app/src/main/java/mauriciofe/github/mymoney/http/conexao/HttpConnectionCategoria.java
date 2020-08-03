package mauriciofe.github.mymoney.http.conexao;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import mauriciofe.github.mymoney.http.parseJson.ParseCategoria;
import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;
import mauriciofe.github.mymoney.models.Categoria;
import mauriciofe.github.mymoney.models.Usuario;

public class HttpConnectionCategoria {
    public static String getDados(String uri, String token) {
        BufferedReader reader = null;
        try {

            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", token);
            conn.setDoOutput(false);

            StringBuilder stringBuilder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            Log.i("retornoApi", "getDados: " + conn.getResponseCode());
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String postDados(String uri) {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Bacon");
        String urlParameters = ParseCategoria.converterParaJSON(categoria);
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

    public static String putDados(String uri) {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Bacon");
        String urlParameters = ParseCategoria.converterParaJSON(categoria);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);

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
            Log.i("retornoApi", "PutDados: " + conn.getResponseCode());
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteDados(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            Log.i("retornoApi", "deleteDados: " + conn.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
