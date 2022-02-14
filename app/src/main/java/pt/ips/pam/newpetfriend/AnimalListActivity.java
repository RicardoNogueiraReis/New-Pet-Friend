package pt.ips.pam.newpetfriend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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

    public static final String ANIMAL_TYPE = "ANIMAL_TYPE";

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
            String animalFiltro = extras.getString(ANIMAL_TYPE);
            switch(animalFiltro){
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
        try{
            executorService.awaitTermination(2L, TimeUnit.SECONDS);
            if(executorService.isShutdown())
                setAdapter();
            else{
                executorService.shutdown();
                setAdapter();
            }
        }
        catch(java.lang.InterruptedException e){

        }

    }

    private void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(animalList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

}