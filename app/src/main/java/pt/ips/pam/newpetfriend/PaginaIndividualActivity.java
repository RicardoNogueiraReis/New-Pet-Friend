package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PaginaIndividualActivity extends AppCompatActivity {

    public static final String ANIMAL_NAME = "ANIMAL_NAME";
    public static final String ANIMAL_AGE = "ANIMAL_AGE";
    public static final String ANIMAL_GENDER = "ANIMAL_GENDER";
    public static final String ANIMAL_BREED = "ANIMAL_BREED";
    public static final String ANIMAL_VACCINATED = "ANIMAL_VACCINATED";
    public static final String ANIMAL_CASTRATED = "ANIMAL_CASTRATED";
    public static final String ANIMAL_INSTITUTION = "ANIMAL_INSTITUTION";

    private TextView nome;
    private TextView idade;
    private TextView genero;
    private TextView raca;
    private TextView vacinado;
    private TextView castrado;
    private TextView instituicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logotipo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_pagina_individual);

        nome = (TextView) findViewById(R.id.textViewNomeIndividual);
        idade = (TextView) findViewById(R.id.textViewIdadeIndividual);
        genero = (TextView) findViewById(R.id.textViewGeneroIndividual);
        raca = (TextView) findViewById(R.id.textViewRacaIndividual);
        vacinado = (TextView) findViewById(R.id.textViewVacinadoIndividual);
        castrado = (TextView) findViewById(R.id.textViewCastradoIndividual);
        instituicao = (TextView) findViewById(R.id.textViewInstituicaoIndividual);

        receberDadosDoAnimal();


    }

    private void receberDadosDoAnimal() {
        Bundle extras = getIntent().getExtras();
        nome.setText(extras.getString(ANIMAL_NAME));
        idade.setText((CharSequence) extras.getString(ANIMAL_AGE));

        String generoOutput = extras.getString(ANIMAL_GENDER).equals("Macho") ?
                                getResources().getText(R.string.macho).toString() :
                                getResources().getText(R.string.femea).toString();

        genero.setText(generoOutput);

        raca.setText(String.valueOf(extras.getString(ANIMAL_BREED)));

        //getResources().getText(R.string.cao).toString()

        String vacinadoOutput = extras.getString(ANIMAL_VACCINATED).equals("Sim") ?
                getResources().getText(R.string.sim).toString() :
                getResources().getText(R.string.nao).toString();

        vacinado.setText(vacinadoOutput);

        String castradoOutput = extras.getString(ANIMAL_CASTRATED).equals("Sim") ?
                getResources().getText(R.string.sim).toString() :
                getResources().getText(R.string.nao).toString();

        castrado.setText(castradoOutput);

        instituicao.setText(extras.getString(ANIMAL_INSTITUTION));
    }
}