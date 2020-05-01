package com.balatech.uber.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balatech.uber.R;
import com.balatech.uber.config.ConfiguracaoFirebase;
import com.balatech.uber.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    public void abrirTelaLogin(View view){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void abrirTelaCadastro(View view){
        startActivity(new Intent(MainActivity.this,CadastroActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        UsuarioFirebase.redirecionaUsuarioLogado(MainActivity.this);
    }
}
