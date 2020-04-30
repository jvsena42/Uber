package com.balatech.uber.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.balatech.uber.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Switch switchTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Inicializar componentes
        campoNome = findViewById(R.id.editCadastroNome);
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        switchTipoUsuario = findViewById(R.id.switchTipoUsuario);
    }

    public void validarCadastroUsuario(){

        //Recuperar textos dos campos
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if (!textoNome.isEmpty() && !textoEmail.isEmpty() && !textoSenha.isEmpty()){

        }else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }


    }
}
