package pt.ips.pam.newpetfriend;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tipo_animal")
    private String tipoAnimal;

    @ColumnInfo(name = "nome_animal")
    private String nomeAnimal;

    @ColumnInfo(name = "idade")
    private int idade;

    @ColumnInfo(name = "genero")
    private String genero;

    @ColumnInfo(name = "raca")
    private String raca;

    @ColumnInfo(name = "vacinado")
    private boolean vacinado;

    @ColumnInfo(name = "castrado")
    private boolean castrado;

    @ColumnInfo(name = "instituicao")
    private String instituicao;

    public Animal(String tipoAnimal, String nomeAnimal, int idade, String genero, String raca,
                  boolean vacinado, boolean castrado, String instituicao) {
        this.tipoAnimal = tipoAnimal;
        this.nomeAnimal = nomeAnimal;
        this.idade = idade;
        this.genero = genero;
        this.raca = raca;
        this.vacinado = vacinado;
        this.castrado = castrado;
        this.instituicao = instituicao;
    }

    public void setId(int id) { this.id = id; }

    public void setTipoAnimal(String tipoAnimal) throws NullPointerException {
        switch (tipoAnimal){
            case "Gato": case "Cão":
                this.tipoAnimal = tipoAnimal;
            default:
                throw new NullPointerException("ERRO: Tipo de animal tem de ser cão ou gato");
        }
    }

    public void setNomeAnimal(String nomeAnimal) throws NullPointerException {
        if(nomeAnimal.trim().isEmpty())
            throw new NullPointerException();

        this.nomeAnimal = nomeAnimal;
    }

    public void setIdade(int idade) throws NullPointerException {
        if(idade < 0)
            throw new NullPointerException("ERRO: Idade não pode ser menor que 0");

        this.idade = idade;
    }

    public void setGenero(String genero) throws NullPointerException {
        switch(genero){
            case "Macho": case "Femea":
                this.genero = genero;
            default:
                throw new NullPointerException("ERRO: Genero tem de ser ou Macho ou Fêmea");
        }
    }

    public void setRaca(String raca) throws NullPointerException{
        if(raca.trim().isEmpty())
            throw new NullPointerException();

        this.raca = raca;
    }


    public void setVacinado(boolean vacinado) { this.vacinado = vacinado; }

    public void setInstituicao(String instituicao) throws NullPointerException {
        if(instituicao.trim().isEmpty())
            throw new NullPointerException();

        this.instituicao = instituicao;
    }

    public void setCastrado(boolean castrado){
        this.castrado = castrado;
    }

    public int getId() { return id; }

    public String getNomeAnimal() { return nomeAnimal; }

    public int getIdade() { return idade; }

    public String getGenero() { return genero; }

    public String getRaca() { return raca; }

    public String getTipoAnimal() { return tipoAnimal; }

    public boolean isVacinado() { return vacinado; }

    public String isVacinadoVerbose() {
        if(vacinado)
            return "Sim";

        return "Não";
    }

    public boolean isCastrado() { return castrado; }

    public String isCastradoVerbose() {
        if(castrado)
            return "Sim";

        return "Não";
    }

    public String getInstituicao() { return instituicao; }

    @NonNull
    @Override
    public String toString(){
        return " Animal [id=" + id
                + ", tipoAnimal=" + tipoAnimal
                + ", nomeAnimal=" + nomeAnimal
                + ", idade=" + idade
                + ", genero=" + genero
                + ", raca=" + raca
                + ", vacinado=" + isVacinadoVerbose()
                + ", castrado=" + isCastradoVerbose()
                + ", instituicao=" + instituicao + "]";

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(nomeAnimal);
        dest.writeInt(idade);
        dest.writeBoolean(vacinado);

    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    protected Animal(Parcel in) {
        id = in.readInt();
        tipoAnimal = in.readString();
        nomeAnimal = in.readString();
        idade = in.readInt();
        genero = in.readString();
        raca = in.readString();
        vacinado = in.readByte() != 0;
        castrado = in.readByte() != 0;
        instituicao = in.readString();
    }
}