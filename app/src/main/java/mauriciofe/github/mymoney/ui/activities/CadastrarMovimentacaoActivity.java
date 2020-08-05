package mauriciofe.github.mymoney.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.http.conexao.HttpConnectionCategoria;
import mauriciofe.github.mymoney.http.parseJson.ParseCategoria;
import mauriciofe.github.mymoney.http.ssl.ConferirSsl;
import mauriciofe.github.mymoney.models.Categoria;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.categoria.GetDadosCategoria;

public class CadastrarMovimentacaoActivity extends AppCompatActivity {
    Usuario usuario;
    String token;
    Categoria categoria;
    private List<Categoria> categoriaList;
    EditText edtDescricao;
    EditText edtValor;
    EditText edtData;
    EditText edtObservacoes;
    Spinner spnCategoria;
    Spinner spnTipoMovimentacao;
    Spinner spnRepeticao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_movimentacao);
        preencheComponentes();
        recebeDadosDaIntent();
        ConferirSsl.trustEveryone();
        buscarCateroria("https://192.168.0.14:44325/api/categorias");
    }

    private void carregarCategorias() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoriaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapter);

    }

    private void buscarCateroria(String uri) {
        GetDadosCategoria task = new GetDadosCategoria(this);
        task.execute(uri, token);
    }

    private void recebeDadosDaIntent() {
        Intent intent= getIntent();
        if (intent.hasExtra("usuario")&& intent.hasExtra("token")){
           usuario = (Usuario) intent.getSerializableExtra("usuario");
           token = intent.getStringExtra("token");
        }
    }

    private void preencheComponentes() {
        edtDescricao = findViewById(R.id.cadastrar_movimentacao_edtDescricao);
        edtValor = findViewById(R.id.cadastrar_movimentacao_edtValor);
        edtData = findViewById(R.id.cadastrar_movimentacao_edtData);
        edtObservacoes = findViewById(R.id.cadastrar_movimentacao_edtObservacoes);
        spnCategoria = findViewById(R.id.cadastrar_movimentacao_spnCategoria);
        spnTipoMovimentacao = findViewById(R.id.cadastrar_movimentacao_spnTipoMovimentacao);
        spnRepeticao = findViewById(R.id.cadastrar_movimentacao_spnRepeticao);
    }

    public class GetDadosCategoria extends AsyncTask<String, String, String> {
        private Context context;
        public GetDadosCategoria(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpConnectionCategoria.getDados(params[0], params[1]);
            return content;
        }

        @Override
        protected void onPostExecute(String conteudo) {
            categoriaList = ParseCategoria.getCategoriasJson(conteudo);
            if (categoriaList != null) {
                carregarCategorias();
            }
        }
    }


}