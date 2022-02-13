package pt.ips.pam.newpetfriend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Animal> animalList;

    public RecyclerAdapter(ArrayList<Animal> animalList){
        this.animalList = animalList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeTexto;
        private TextView idadeTexto;
        private TextView tipoTexto;

        public MyViewHolder(final View view){
            super(view);
            nomeTexto = view.findViewById(R.id.textViewNome);
            idadeTexto = view.findViewById(R.id.textViewIdade);
            tipoTexto = view.findViewById(R.id.textViewTipoAnimal);
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
        String nome = animalList.get(position).getNomeAnimal();
        int idade = animalList.get(position).getIdade();
        String tipo = animalList.get(position).getTipoAnimal();

        holder.nomeTexto.setText(nome);
        holder.idadeTexto.setText((CharSequence) String.valueOf(idade));
        holder.tipoTexto.setText(tipo);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
