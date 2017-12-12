package br.com.satoruchannel.pizzaria;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextInputLayout username;
    @BindView(R.id.password)
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validaCampo(username);
            }
        });

        password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validaCampo(password);
            }
        });
    }

    @OnClick(R.id.btConectar)
    public void conectar() {
        if (validaCampo(username) && validaCampo(password)) {
            Intent in = new Intent(this, MainActivity.class);
            in.putExtra("USERNAME", username.getEditText().getText().toString());
            in.putExtra("PASSWORD", password.getEditText().getText().toString());
            startActivity(in);
        }
    }

    private boolean validaCampo(TextInputLayout tilCampo) {
        if (tilCampo.getEditText().getText().toString().isEmpty()){
            tilCampo.setError("O campo não foi preenchido");
            return false;
        } else {
            tilCampo.setError(null);
        }
        return true;
    }
}
