package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String VALOR_EMAIL = "EMAIL";
    private static final String VALOR_PASS = "PASSWORD";

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

        guardarDados();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!lerDados())
                    return;
                
                Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainActivity);
                Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposEstaoPreenchidos(email, password)){
                    return;
                }
                guardarUtilizador();
                email = (EditText) findViewById(R.id.editTextEmail);
                email.setText("");
                password = (EditText) findViewById(R.id.editTextPalavraPasse);
                password.setText("");
                Toast.makeText(LoginActivity.this, "Efetue o login com a conta criada!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean camposEstaoPreenchidos(EditText email, EditText password){
        boolean isEmailEmpty = email.getText().toString().isEmpty();
        boolean isPasswordEmpty = password.getText().toString().isEmpty();

        if(isEmailEmpty && isPasswordEmpty){
            Toast.makeText(LoginActivity.this, getResources().getText(R.string.erro_email_e_password_vazio), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(isEmailEmpty){
            Toast.makeText(LoginActivity.this, getResources().getText(R.string.erro_email_vazio), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(isPasswordEmpty){
            Toast.makeText(LoginActivity.this, getResources().getText(R.string.erro_password_vazio), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void guardarDados(){
        String email = "admin@ips.pt";
        String pass = "admin";

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(VALOR_EMAIL, email);
        edit.putString(VALOR_PASS, pass);

        edit.commit();
    }

    public void guardarUtilizador(){
        EditText editEmail = findViewById(R.id.editTextEmail);
        EditText editPass = findViewById(R.id.editTextPalavraPasse);
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(VALOR_EMAIL, email);
        edit.putString(VALOR_PASS, pass);

        edit.commit();
    }

    public boolean lerDados(){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        EditText editEmail = findViewById(R.id.editTextEmail);
        EditText editPass = findViewById(R.id.editTextPalavraPasse);
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();

        if (!email.equals(sharedPref.getString(VALOR_EMAIL, "")) || !pass.equals(sharedPref.getString(VALOR_PASS, ""))) 
            return false;
        else
            return true;
    }
}