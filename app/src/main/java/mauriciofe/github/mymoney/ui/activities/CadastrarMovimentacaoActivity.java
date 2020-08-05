package mauriciofe.github.mymoney.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.Usuario;

public class CadastrarMovimentacaoActivity extends AppCompatActivity {
    Usuario usuario;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_movimentacao);
        Intent intent= getIntent();
        if (intent.hasExtra("usuario")&& intent.hasExtra("token")){
            Toast.makeText(this, "Deu", Toast.LENGTH_SHORT).show();
        }
    }
}