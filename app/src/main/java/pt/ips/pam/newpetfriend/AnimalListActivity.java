package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AnimalListActivity extends AppCompatActivity {

    private ArrayList<Animal> animalList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);
        recyclerView = findViewById(R.id.recyclerViewAnimais);
        animalList = new ArrayList<>();

        setAnimalInfo();
        setAdapter();
    }

    private void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(animalList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setAnimalInfo(){
        animalList.add(new Animal("Cão", "Jorge", 3, "Macho", "pincher", true, false, "teste"));
        animalList.add(new Animal("Gato", "Jorgina", 4, "Fêmea", "siames", true, false, "teste"));
    }
}