package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    /*private Button button;*/

    public static final String CURRENT_USER = "CURRENT_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        //botão para testar mapa
        /*button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });*/
=======

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        Bundle extras = getIntent().getExtras();
        if(extras.getString(CURRENT_USER).equals("admin@ips.pt"))
            getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.admin) {
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
>>>>>>> de7d7a2e07181ef365352ccca49e9cbafcb1decd
    }

    /*public void openMap(){
        Intent intent = new Intent(this, MapsNewPetFriend.class);
        startActivity(intent);
    }*/
}