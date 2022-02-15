package pt.ips.pam.newpetfriend;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Animal> animalList;
    private Activity activity;


    public RecyclerAdapter(ArrayList<Animal> animalList, Activity activity) {
        this.animalList = animalList;
        this.activity = activity;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout animalLinha;
        private TextView nomeTexto;
        private TextView idadeTexto;
        private TextView tipoTexto;

        public MyViewHolder(final View view) {
            super(view);
            nomeTexto = view.findViewById(R.id.textViewNome);
            idadeTexto = view.findViewById(R.id.textViewIdade);
            tipoTexto = view.findViewById(R.id.textViewTipoAnimal);
            animalLinha = view.findViewById(R.id.ConstraintLayoutLinhaAnimal);
        }

    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal_recycler, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String tipo = animalList.get(position).getTipoAnimal();
        String nome = animalList.get(position).getNomeAnimal();
        String idade = String.valueOf(animalList.get(position).getIdade());

        String genero = animalList.get(position).getGenero();
        String raca = animalList.get(position).getRaca();
        String vacinado = animalList.get(position).isVacinadoVerbose();
        String castrado = animalList.get(position).isCastradoVerbose();
        String instituicao = animalList.get(position).getInstituicao();

        holder.nomeTexto.setText(nome);
        holder.idadeTexto.setText((CharSequence) String.valueOf(idade));
        holder.tipoTexto.setText(tipo);

        holder.animalLinha.setOnClickListener(view -> {
            Intent intent = new Intent(activity, PaginaIndividualActivity.class);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_NAME, nome);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_AGE, idade);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_GENDER, genero);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_BREED, raca);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_VACCINATED, vacinado);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_CASTRATED, castrado);
            intent.putExtra(PaginaIndividualActivity.ANIMAL_INSTITUTION, instituicao);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
