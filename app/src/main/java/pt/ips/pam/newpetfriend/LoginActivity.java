package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btnLogin;
    Button btnRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPalavraPasse);
        btnLogin = (Button) findViewById(R.id.buttonIniciarSessao);
        btnRegistar = (Button) findViewById(R.id.buttonRegistar);

        guardarDadosPredefinidos();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!lerDados() || !validarEmail(email))
                    return;
                
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.CURRENT_USER, email.getText().toString());
                startActivity(intent);
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposEstaoPreenchidos(email, password) || !validarEmail(email))
                    return;

                guardarUtilizador();
            }
        });
    }

    private boolean validarEmail(EditText email) {
        String emailInput = email.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(LoginActivity.this,
                    getResources().getText(R.string.erro_email_invalido),
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    private boolean camposEstaoPreenchidos(EditText email, EditText password){
        int isEmailEmpty = email.getText().toString().trim().isEmpty() ? 1 : 0;
        int isPasswordEmpty = password.getText().toString().trim().isEmpty() ? 2 : 0;

        switch(isEmailEmpty+isPasswordEmpty){
            case 1:
                Toast.makeText(LoginActivity.this,
                        getResources().getText(R.string.erro_email_vazio),
                        Toast.LENGTH_SHORT).show();
                return false;
            case 2:
                Toast.makeText(LoginActivity.this,
                        getResources().getText(R.string.erro_password_vazia),
                        Toast.LENGTH_SHORT).show();
                return false;
            case 3:
                Toast.makeText(LoginActivity.this,
                        getResources().getText(R.string.erro_email_e_password_vazio),
                        Toast.LENGTH_SHORT).show();
                return false;
        }

        return true;
    }


    public void guardarDadosPredefinidos(){
        String email = "admin@ips.pt";
        String pass = "admin";

        SharedPreferences sharedPref = getSharedPreferences("Utilizador", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();

        edit.putString(email + pass + "data", email + "\n" + pass);
        edit.commit();

    }

    public void guardarUtilizador(){
        String getEmail = email.getText().toString().trim();
        String getPass = password.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("Utilizador", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();

        if(sharedPref.contains(getEmail)){
            Toast.makeText(LoginActivity.this,
                    getResources().getText(R.string.erro_utilizador_ja_existe), Toast.LENGTH_SHORT).show();
            return;
        }

        edit.putString(getEmail + getPass + "data", getEmail + "\n" + getPass);
        edit.commit();

        email.setText("");
        password.setText("");

        Toast.makeText(LoginActivity.this,
                getResources().getText(R.string.efetue_login_conta_criada),
                        Toast.LENGTH_SHORT).show();
    }

    public boolean lerDados(){
        SharedPreferences sharedPref = getSharedPreferences("Utilizador", Context.MODE_PRIVATE);

        String getEmail = email.getText().toString();
        String getPass = password.getText().toString();
        String mensagemErro = getResources().getString(R.string.erro_utilizador_nao_encontrado);

        String utilizador = sharedPref.getString(getEmail + getPass + "data", mensagemErro);

        if(utilizador.equals(mensagemErro))
            return false;

        return true;

    }
}