package mauriciofe.github.mymoney.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.http.ssl.ConferirSsl;
import mauriciofe.github.mymoney.models.Categoria;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.categoria.GetDadosCategoria;
import mauriciofe.github.mymoney.tasks.tipoMovimentacao.GetTipoMovimentacao;

public class CadastrarMovimentacaoActivity extends AppCompatActivity {
    Usuario usuario;
    String token;
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
        buscarTipoMovimentacao("https://192.168.0.14:44325/api/tipoMovimentacao");
        spnCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void buscarTipoMovimentacao(String uri) {
        GetTipoMovimentacao tasks = new GetTipoMovimentacao(this, spnTipoMovimentacao);
        tasks.execute(uri, token);
    }

    private void buscarCateroria(String uri) {
        GetDadosCategoria task = new GetDadosCategoria(this, spnCategoria);
        task.execute(uri, token);
    }

    private void recebeDadosDaIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("usuario") && intent.hasExtra("token")) {
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
}