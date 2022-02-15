package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AnimalListActivity extends AppCompatActivity {

    private ArrayList<Animal> animalList;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private ExecutorService executorService;

    public static final String TIPO_ANIMAL = "TIPO_ANIMAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        executorService = Executors.newCachedThreadPool();
        db = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerViewAnimais);

        animalList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        executorService.execute(() -> {
            String animalFiltro = extras.getString(TIPO_ANIMAL);
            switch (animalFiltro) {
                case "ambos":
                    animalList = new ArrayList<>(db.animalDao().getAll());
                    break;
                case "Cão":
                    animalList = new ArrayList<>(db.animalDao().findByAnimalType(animalFiltro));
                    break;
                case "Gato":
                    animalList = new ArrayList<>(db.animalDao().findByAnimalType(animalFiltro));
            }

        });

        executorService.shutdown();
        try {
            executorService.awaitTermination(2L, TimeUnit.SECONDS);
            if (executorService.isShutdown())
                setAdapter();
            else {
                executorService.shutdown();
                setAdapter();
            }
        } catch (java.lang.InterruptedException e) {
            Toast.makeText(this,
                    getResources().getText(R.string.erro_inesperado).toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    // Cria o LinearLayout para implementar as linhas dos registos da base de dados
private void setAdapter() {
    RecyclerAdapter adapter = new RecyclerAdapter(animalList, this);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
}

}