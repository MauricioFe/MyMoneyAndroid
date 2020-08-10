package mauriciofe.github.mymoney.http.conexao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import mauriciofe.github.mymoney.http.parseJson.ParseMovimentacao;
import mauriciofe.github.mymoney.models.Movimentacoes;

public class HttpConnectionMovimentacoes {
    public static String postDados(String uri, String token, Movimentacoes movimentacoes) {
        StringBuilder stringBuilder;
        String params = ParseMovimentacao.parseForJson(movimentacoes);
        BufferedReader reader = null;
        OutputStreamWriter writer = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("ContentType", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(params);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
