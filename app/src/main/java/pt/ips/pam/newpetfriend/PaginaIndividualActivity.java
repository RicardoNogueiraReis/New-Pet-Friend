package pt.ips.pam.newpetfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PaginaIndividualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logotipo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_pagina_individual);
    }
}