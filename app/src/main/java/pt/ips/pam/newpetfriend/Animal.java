package pt.ips.pam.newpetfriend;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Animal {

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

    public void setId(int id) { this.id = id; }

    public void setTipoAnimal(String tipoAnimal) throws Exception {
        switch (tipoAnimal){
            case "Gato": case "Cão":
                this.tipoAnimal = tipoAnimal;
            default:
                throw new Exception("ERRO: Tipo de animal tem de ser ou cão ou gato");
        }
    }

    public void setNomeAnimal(String nomeAnimal) throws NullPointerException {
        if(nomeAnimal.trim().isEmpty())
            throw new NullPointerException();

        this.nomeAnimal = nomeAnimal;
    }

    public void setIdade(int idade) throws Exception {
        if(idade < 0)
            throw new Exception("ERRO: Idade não pode ser menor que 0");

        this.idade = idade;
    }

    public void setGenero(String genero) throws Exception {
        switch(genero){
            case "Macho": case "Femea":
                this.genero = genero;
            default:
                throw new Exception("ERRO: Genero tem de ser ou Macho ou Fêmea");
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

    public boolean isCastradoVerbose() {
        if(castrado)
            return "Sim";
        return "Não"
    }

    public String getDoencaIndex(int i) { return doencas.get(i); }

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
                + ", castrado=" + getDoencasPorTexto()
                + ", instituicao=" + instituicao + "]";

    }

}
