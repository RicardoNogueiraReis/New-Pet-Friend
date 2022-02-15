package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    /*private Button button;*/
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public static final String CURRENT_USER = "CURRENT_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logotipo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        Button btnCao = (Button) findViewById(R.id.buttonCao);
        Button btnGato = (Button) findViewById(R.id.buttonGato);
        Button btnAmbos = (Button) findViewById(R.id.buttonAmbos);
        Button btnMapa = (Button) findViewById(R.id.buttonMapa);
        Intent intent = new Intent(this, AnimalListActivity.class);
        Intent intentMap = new Intent(this, MapsNewPetFriend.class);

        btnCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(AnimalListActivity.ANIMAL_TYPE, "Cão");
                startActivity(intent);
            }
        });

        btnGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(AnimalListActivity.ANIMAL_TYPE, "Gato");
                startActivity(intent);
            }
        });

        btnAmbos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(AnimalListActivity.ANIMAL_TYPE, "ambos");
                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMap);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Bundle extras = getIntent().getExtras();
        if(extras.getString(CURRENT_USER).equals("admin@ips.pt"))
            getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.admin) {
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}