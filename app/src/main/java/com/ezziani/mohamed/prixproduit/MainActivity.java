package com.ezziani.mohamed.prixproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText prix, nombreProduit, TVA;
        final TextView prixHt, prixTTC;
        Button prixTotal, anuuler;



        prix = (EditText) findViewById(R.id.prixProduit);
        nombreProduit = (EditText) findViewById(R.id.nombreProduit);
        prixHt = (TextView) findViewById(R.id.etResultatprixHT);
        prixTTC = (TextView)findViewById(R.id.etResultatPrixTTC);
        TVA = (EditText) findViewById(R.id.tva);
        prixTotal = (Button) findViewById(R.id.buttonPrixTotal);
        anuuler = (Button) findViewById(R.id.buttonAnnuler);

        prixTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double prixProduit, tva;
                final int nbProduit;

                if(prix.getText().toString().isEmpty() && nombreProduit.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.errMsg1, Toast.LENGTH_SHORT).show();
                } else if(nombreProduit.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.errMsg2, Toast.LENGTH_SHORT).show();
                }else if(prix.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.errMsg3, Toast.LENGTH_SHORT).show();
                } else {
                    prixProduit = Double.parseDouble(prix.getText().toString());
                    nbProduit = Integer.parseInt(nombreProduit.getText().toString());

                    if (TVA.getText().toString().isEmpty()) {
                        tva = 0.18;
                        prixHt.setText(String.valueOf(prixProduit * nbProduit));
                        prixTTC.setText(String.valueOf(prixProduit * nbProduit * (1 + tva)));

                    } else {
                        tva = Double.parseDouble(TVA.getText().toString());
                        if (tva < 0 || tva > 1) {
                            Toast.makeText(getApplicationContext(), R.string.tvaErrMsg, Toast.LENGTH_SHORT).show();
                        } else if (prixProduit <= 0) {
                            Toast.makeText(getApplicationContext(), R.string.prixProduitErrMsg, Toast.LENGTH_SHORT).show();
                        } else {
                            prixHt.setText(String.valueOf(prixProduit * nbProduit));
                            prixTTC.setText(String.valueOf(prixProduit * nbProduit * (1 + tva)));
                        }
                    }
                }
            }
        });
        anuuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prix.setText("");
                nombreProduit.setText("");
                TVA.setText("");
                prixHt.setText("");
                prixTTC.setText("");
            }
        });


    }
}
