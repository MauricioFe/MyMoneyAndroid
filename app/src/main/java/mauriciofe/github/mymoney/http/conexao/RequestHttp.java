package mauriciofe.github.mymoney.http.conexao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHttp {

    private String url;
    private String metodo = "GET";
    private Map<String, String> paramentros = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Map<String, String> getParametros() {
        return paramentros;
    }

    public void setParamentros(Map<String, String> paramentros) {
        this.paramentros = paramentros;
    }

    public void setParametro(String key, String value) {
        paramentros.put(key, value);
    }

    public String getEncodedParametros() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : paramentros.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(paramentros.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }

            stringBuilder.append(key + "=" + value);
        }
        return stringBuilder.toString();
    }
}
