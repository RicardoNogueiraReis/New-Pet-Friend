package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    }

    private boolean verificarCampos(){
        boolean isEmailEmpty = email.getText().toString().isEmpty();
        boolean isPasswordEmpty = password.getText().toString().isEmpty();

        if(isEmailEmpty && isPasswordEmpty)
            Toast.makeText(LoginActivity.this, "@string/erro_email_vazio", Toast.LENGTH_SHORT).show();
        else if(isEmailEmpty)
            Toast.makeText(LoginActivity.this, "@string/erro_email_vazio", Toast.LENGTH_SHORT).show();
        else if(isPasswordEmpty)
            Toast.makeText(LoginActivity.this, "@string/erro_password_vazia", Toast.LENGTH_SHORT).show();

        return true;
    }
}