package mauriciofe.github.mymoney.ui.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mauriciofe.github.mymoney.R;

public class ResgisterActivity extends AppCompatActivity {
    Button btnEntrar;
    EditText edtEmail;
    EditText edtSenha;
    TextView txtPossuoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
        btnEntrar = findViewById(R.id.register_btn_entrar);
        edtEmail = findViewById(R.id.register_editText_email);
        edtSenha = findViewById(R.id.register_editText_senha);
        txtPossuoLogin = findViewById(R.id.register_txtPossuo_cadastro);

        txtPossuoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResgisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}