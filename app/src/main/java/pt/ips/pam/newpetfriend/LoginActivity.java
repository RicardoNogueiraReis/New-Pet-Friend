package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText Email;
    EditText password;
    Button btnLogin;
    Button btnRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPalavraPasse);
        btnLogin = (Button) findViewById(R.id.buttonIniciarSessao);
        btnRegistar = (Button) findViewById(R.id.buttonRegistar);
    }
}