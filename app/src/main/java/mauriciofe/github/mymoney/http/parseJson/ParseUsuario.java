package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;

import mauriciofe.github.mymoney.models.Usuario;

public class ParseUsuario {

    public static String login(Usuario usuario) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("email").value(usuario.getEmail());
            js.key("senha").value(usuario.getSenha());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }

    public static String parseToken(String token) {
        String _token = "Bearer ";
        if (token != null) {
            try {
                JSONObject jsonObject = new JSONObject(token);
                _token += jsonObject.getString("token");
                return _token;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
