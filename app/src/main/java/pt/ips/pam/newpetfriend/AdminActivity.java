package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminActivity extends AppCompatActivity  implements
        AnimalListDialogFragment.AnimalListDialogFragmentListener {

    private AppDatabase db;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    RadioButton radioCao;
    RadioButton radioGato;
    EditText editNome;
    EditText editIdade;
    RadioButton radioMacho;
    RadioButton radioFemea;
    EditText editRaca;
    CheckBox checkVacinado;
    CheckBox checkCastrado;
    EditText editInstituicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        db = AppDatabase.getInstance(this);

        radioCao = findViewById(R.id.radioButtonCao);
        radioGato = findViewById(R.id.radioButtonGato);
        editNome = findViewById(R.id.editTextNome);
        editIdade = findViewById(R.id.editTextIdadeSigned);
        radioMacho = findViewById(R.id.radioButtonMacho);
        radioFemea = findViewById(R.id.radioButtonFemea);
        editRaca = findViewById(R.id.editTextRaca);
        checkVacinado = findViewById(R.id.checkBoxVacinado);
        checkCastrado = findViewById(R.id.checkBoxCastrado);
        editInstituicao = findViewById(R.id.editTextInstituicao);

        Button buttonAdd = findViewById(R.id.buttonAdicionar);
        buttonAdd.setOnClickListener(view -> guardarAnimal());

        //vamos associar o método apagarUser() ao clique no botao delete
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(view -> apagarAnimal());

        //vamos associar o método pesquisarUser() ao clique no botao search
        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pesquisarAnimal();
                String nome = editNome.getText().toString();

                executorService.execute(() -> {
                    ArrayList<Animal> animals;
                    if(nome.trim().isEmpty())
                        animals = new ArrayList<>(db.animalDao().getAll());
                    else
                        animals = new ArrayList<>(db.animalDao().findByName(nome));

                    runOnUiThread(() -> {
                        AnimalListDialogFragment dialog = AnimalListDialogFragment.newInstance(animals);
                        dialog.show(getSupportFragmentManager(), "fragment_user_list_dialog");
                    });
                });
            }
        });

    }


    private boolean camposEstaoVazios(){

        boolean[] checkCampos = {
                radioCao.isChecked() || radioGato.isChecked(),
                !editNome.getText().toString().trim().isEmpty(),
                !editIdade.getText().toString().trim().isEmpty(),
                radioMacho.isChecked() || radioFemea.isChecked(),
                !editRaca.getText().toString().trim().isEmpty(),
                !editInstituicao.getText().toString().trim().isEmpty()
        };

        for(boolean campo: checkCampos)
            if(!campo){
                Toast.makeText(AdminActivity.this,
                        "ERRO: Ainda falta informação por adicionar",
                        Toast.LENGTH_SHORT).show();
                return true;
            }

        return false;
    }

    private void guardarAnimal() {

        //validar os campos para verificar se estão preenchidos
        if (camposEstaoVazios())
            return;

        // Se for cão então não é gato e vice-versa
        String tipoAnimal = radioCao.isChecked() ? "Cão" : "Gato";
        String nome = editNome.getText().toString().trim();

        int idade = Integer.parseInt(editIdade.getText().toString().trim());

        // Se for macho então não é fêmea e vice-versa
        String genero = radioMacho.isChecked() ? "Macho" : "Fêmea";
        String raca = editRaca.getText().toString().trim();

        boolean vacionado = checkVacinado.isChecked();
        boolean castrado = checkCastrado.isChecked();

        String instituicao = editInstituicao.getText().toString().trim();

        Animal animal = new Animal(tipoAnimal, nome, idade, genero, raca, vacionado, castrado, instituicao);

        executorService.execute(() -> {
            List<Animal> animais = db.animalDao().getAll();

            int indiceUpdate = searchAnimalByName(animais, nome);

            // Se já existir um animal com esse nome, é atualizado os seus dados
            if (indiceUpdate != -1) {
                animal.setId(animais.get(indiceUpdate).getId());
                db.animalDao().update(animal);
                runOnUiThread(() ->
                        Toast.makeText(this, getResources().getText(R.string.animal_atualizado).toString(),
                                Toast.LENGTH_SHORT).show()
                );
            } else {
                db.animalDao().insert(animal);
                runOnUiThread(() ->
                        Toast.makeText(this, getResources().getText(R.string.animal_inserido).toString(),
                                Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    private int searchAnimalByName(List<Animal> list, String nomeAnimal) {
        int foundIndex = -1;

        for (Animal currentAnimal : list)
            if (currentAnimal.getNomeAnimal().equals(nomeAnimal))
                foundIndex = list.indexOf(currentAnimal);

        return foundIndex;
    }


    private void apagarAnimal() {
        String nome = editNome.getText().toString();

        executorService.execute(() -> {
            List<Animal> animais = db.animalDao().getAll();
            int indiceDelete = searchAnimalByName(animais, nome);

            if (indiceDelete != -1) {

                Animal animal = animais.get(indiceDelete);
                db.animalDao().delete(animal);
                runOnUiThread(() -> {
                    mostrarAnimal(null);
                    Toast.makeText(this, getResources().getText(R.string.animal_apagado).toString(),
                            Toast.LENGTH_SHORT).show();
                });
            } else {
                runOnUiThread(() ->
                        Toast.makeText(this, getResources().getText(R.string.animal_nao_encontrado).toString(),
                                Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    private void mostrarAnimal(Animal animal) {
        //obter referencias para widgets
        if (animal == null) {
            radioCao.setChecked(false);
            radioGato.setChecked(false);
            editNome.setText("");
            editIdade.setText("");
            radioMacho.setChecked(false);
            radioFemea.setChecked(false);
            editRaca.setText("");
            checkVacinado.setChecked(false);
            checkCastrado.setChecked(false);
            editInstituicao.setText("");
            return;
        }

        switch(animal.getTipoAnimal()){
            case "Cão":
                radioCao.setChecked(true);
                radioGato.setChecked(false);
                break;
            case "Gato":
                radioCao.setChecked(false);
                radioGato.setChecked(true);
        }

        editNome.setText((CharSequence) animal.getNomeAnimal());
        editIdade.setText((CharSequence) String.valueOf(animal.getIdade()));

        switch(animal.getGenero()){
            case "Macho":
                radioMacho.setChecked(true);
                radioFemea.setChecked(false);
                break;
            case "Fêmea":
                radioMacho.setChecked(false);
                radioFemea.setChecked(true);
        }

        editRaca.setText((CharSequence) animal.getTipoAnimal());
        checkVacinado.setChecked(animal.isVacinado());
        checkCastrado.setChecked(animal.isCastrado());
        editInstituicao.setText((CharSequence) animal.getInstituicao());
    }

    private void pesquisarAnimal() {
        String nome = editNome.getText().toString();

        executorService.execute(() -> {
            List<Animal> users = db.animalDao().getAll();
            int indiceDelete = searchAnimalByName(users, nome);

            if (indiceDelete != -1)
                runOnUiThread(() ->
                        Toast.makeText(this, getResources().getText(R.string.animal_encontrado).toString(),
                                Toast.LENGTH_SHORT).show()
                );
            else
                runOnUiThread(() ->
                        Toast.makeText(this, getResources().getText(R.string.animal_nao_encontrado).toString(),
                                Toast.LENGTH_SHORT).show()
                );
        });
    }

    @Override
    public void onAnimalSeleced(Animal animal) {
        mostrarAnimal(animal);
        Toast.makeText(this, animal.getNomeAnimal(), Toast.LENGTH_LONG).show();
    }
}