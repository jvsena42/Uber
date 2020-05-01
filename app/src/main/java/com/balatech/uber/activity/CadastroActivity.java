package com.balatech.uber.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.balatech.uber.R;
import com.balatech.uber.config.ConfiguracaoFirebase;
import com.balatech.uber.helper.UsuarioFirebase;
import com.balatech.uber.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Switch switchTipoUsuario;

    private FirebaseAuth autenticacao;

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

    public void validarCadastroUsuario(View view){

        //Recuperar textos dos campos
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if (!textoNome.isEmpty() && !textoEmail.isEmpty() && !textoSenha.isEmpty()){

            Usuario usuario = new Usuario();
            usuario.setNome(textoNome);
            usuario.setEmail(textoEmail);
            usuario.setSenha(textoSenha);
            usuario.setTipo(verificaTipoUsuario());

            cadastrarUsuario(usuario);

        }else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(final Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    try {
                        String idUsuario = task.getResult().getUser().getUid();
                        usuario.setId(idUsuario);
                        usuario.salvar();
                        Toast.makeText(CadastroActivity.this, "Cadastrado oom sucesso!", Toast.LENGTH_SHORT).show();

                        //Atualizar nome no UserProfile
                        UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());

                        /*Redireciona o usuário com base no seu tipo
                         * se for passageiro, chama a activity maps
                         * se for motorista, chama a activity requisicoes*/

                        if (verificaTipoUsuario() == "P"){
                            startActivity(new Intent(CadastroActivity.this,MainActivity.class));
                            finish();
                        }else {
                            startActivity(new Intent(CadastroActivity.this,RequisicoesActivity.class));
                            finish();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch ( FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excecao= "Por favor, digite um e-mail válido";
                    }catch ( FirebaseAuthUserCollisionException e){
                        excecao = "Este conta já foi cadastrada";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: "  + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String verificaTipoUsuario(){
        return switchTipoUsuario.isChecked() ? "M" : "P";
    }


}
