package mauriciofe.github.mymoney.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.http.ssl.ConferirSsl;
import mauriciofe.github.mymoney.models.Movimentacoes;
import mauriciofe.github.mymoney.models.Repeticao;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.categoria.GetDadosCategoria;
import mauriciofe.github.mymoney.tasks.movimentacao.PostMovimentacao;
import mauriciofe.github.mymoney.tasks.repeticao.PostRepeticao;
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
    Button btnCancelar;
    Button btnSalvar;
    Repeticao repeticao;
    Movimentacoes movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_movimentacao);
        preencheComponentes();
        recebeDadosDaIntent();
        ConferirSsl.trustEveryone();
        buscarCateroria("https://192.168.0.14:44303/api/categorias");
        buscarTipoMovimentacao("https://192.168.0.14:44303/api/tipoMovimentacao");
        movimentacao = new Movimentacoes();
        spnCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movimentacao.setCategoria_id(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnTipoMovimentacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movimentacao.setTipoMovimentacao_id(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnRepeticao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnRepeticao.getSelectedItem() != null) ;
                {
                    String repet = spnRepeticao.getSelectedItem().toString();
                    repeticao = new Repeticao();
                    repeticao.setDescricao(repet);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cadastrarRepetição();

    }

    private void cadastrarRepetição() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insereRepeticao("https://192.168.0.14:44303/api/repeticao");
                insereMovimentacao("https://192.168.0.14:44303/api/movimentacoes");
            }
        });
    }

    private void insereMovimentacao(String uri) {
        movimentacao.setUsuario_id(usuario.getId());
        movimentacao.setDescricao(edtDescricao.getText().toString());
        movimentacao.setValor(Double.parseDouble(edtValor.getText().toString()));
        movimentacao.setData(edtData.getText().toString());
        movimentacao.setObservacoes(edtObservacoes.getText().toString());
        PostMovimentacao task = new PostMovimentacao(this, movimentacao);
        task.execute(uri, token);
    }

    private void insereRepeticao(String uri) {
        PostRepeticao task = new PostRepeticao(this, repeticao, movimentacao);
        task.execute(uri, token);
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
        btnSalvar = findViewById(R.id.cadastrar_movimentacao_btnSalvar);
        btnCancelar = findViewById(R.id.cadastrar_movimentacao_btnCancelar);
        List<String> repeticaoList = new ArrayList<>();
        repeticaoList.add("Único");
        repeticaoList.add("Parcelado");
        repeticaoList.add("Fixo");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CadastrarMovimentacaoActivity.this, android.R.layout.simple_spinner_item, repeticaoList);
        spnRepeticao.setAdapter(adapter);
    }
}