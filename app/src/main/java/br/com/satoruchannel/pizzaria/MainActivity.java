package br.com.satoruchannel.pizzaria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.zip.CheckedOutputStream;

import br.com.satoruchannel.pizzaria.model.Pedido;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    Pedido pedido = new Pedido();

    String username;
    String password;

    @BindView(R.id.tvCliente)
    TextView tvCliente;

    @BindView(R.id.cbAtum)
    CheckBox cbAtum;
    @BindView(R.id.cbBacon)
    CheckBox cbBacon;
    @BindView(R.id.cbCalabresa)
    CheckBox cbCalabresa;
    @BindView(R.id.cbPortuguesa)
    CheckBox cbPortuguesa;

    @BindView(R.id.rgTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = getIntent();
        if (in != null) {
            username = in.getStringExtra("USERNAME");
            password = in.getStringExtra("PASSWORD");
        }

        ButterKnife.bind(this);

        tvCliente.setText(String.format("Olá, %s", username, password));

        setListenerCheckbox(cbBacon);
        setListenerCheckbox(cbAtum);
        setListenerCheckbox(cbCalabresa);
        setListenerCheckbox(cbPortuguesa);
    }

    @OnClick(R.id.btFecharPedido)
    public void fecharPedido() {
        pedido.setCliente(username);

        pedido.setTamanho(getTamanhoSelecionado());
        pedido.setFormaPagamento(spPagamentos.getSelectedItem().toString());
        Intent in = new Intent(this, ConfirmarPedidoActivity.class);

        in.putExtra("PEDIDO", pedido);

        startActivity(in);
    }

    private void setListenerCheckbox(final CheckBox checkbox) {
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pedido.addSabor(cbBacon.getText().toString());
                } else {
                    pedido.removerSabor(cbBacon.getText().toString());
                }
            }
        });

    }

    public String getTamanhoSelecionado() {
        return ((RadioButton)findViewById(rgTamanho.getCheckedRadioButtonId())).getText().toString();
    }
}
