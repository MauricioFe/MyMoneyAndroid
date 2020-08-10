package mauriciofe.github.mymoney.http.conexao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import mauriciofe.github.mymoney.http.parseJson.ParseMovimentacao;
import mauriciofe.github.mymoney.models.Movimentacoes;

public class HttpConnectionMovimentacoes {
    public static String postDados(String uri, String token, Movimentacoes movimentacoes) {
        String params = ParseMovimentacao.parseForJson(movimentacoes);
        BufferedReader reader = null;
        OutputStreamWriter writer = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestMethod("POST");
            writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(params);
            writer.flush();

            String line;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Log.i("Code Response", "postDados: " + conn.getResponseCode());
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
