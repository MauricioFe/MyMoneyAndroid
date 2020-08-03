package mauriciofe.github.mymoney.http.parseJson;

import android.util.Log;

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

    public static Usuario getEmailByToken(String token) {
        Usuario usuario = new Usuario();
        String usuarioObj;
        if (token != null) {
            try {
                JSONObject jsonObject = new JSONObject(token);
                usuarioObj = jsonObject.getString("usuario");
                JSONObject obj = new JSONObject(usuarioObj);
                usuario.setEmail(obj.getString("email"));
                usuario.setNome(obj.getString("nome"));
                return usuario;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String converterParaJSON(Usuario usuario) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("nome").value(usuario.getNome());
            js.key("email").value(usuario.getEmail());
            js.key("senha").value(usuario.getSenha());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
