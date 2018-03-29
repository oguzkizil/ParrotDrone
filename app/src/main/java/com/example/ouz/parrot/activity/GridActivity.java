package com.example.ouz.parrot.activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ouz.parrot.R;

import java.io.Console;

public class GridActivity extends AppCompatActivity {

    Button yukari,asagi,sol,sag,donme,uc;
    Dialog yukaripopup,asagipopup,solpopup,sagpopup,donmepopup;
    EditText EtYukari,EtYukari2,EtYukari3,EtAsagi,EtSol,EtSag,EtSag2;
    public static int kontrol =0;
    public static int kontrol1 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        yukari=findViewById(R.id.yukari);
        asagi=findViewById(R.id.asagi);
        sol=findViewById(R.id.sol);
        sag=findViewById(R.id.sag);
        donme=findViewById(R.id.donme);
        uc=findViewById(R.id.uc);

        EtYukari=findViewById(R.id.Etyukari);
        EtYukari2=findViewById(R.id.EtYukari2);
        EtYukari3=findViewById(R.id.EtYukari3);
        EtSol=findViewById(R.id.EtSol);
        EtSag=findViewById(R.id.Etsag);
        EtSag2=findViewById(R.id.EtSag2);

        yukaripopup=new Dialog(this);
        asagipopup=new Dialog(this);
        solpopup=new Dialog(this);
        sagpopup=new Dialog(this);
        donmepopup=new Dialog(this);



        yukari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yukaripopup.setContentView(R.layout.yukaripopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=yukaripopup.findViewById(R.id.editText);
                btntamam=yukaripopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("5") && kontrol==0)
                        {
                            EtYukari.setVisibility(View.VISIBLE);
                            yukaripopup.dismiss();
                            kontrol=1;
                        }
                        else if(popupgiris.getText().toString().equals("4") && kontrol==2)
                        {
                            EtYukari2.setVisibility(View.VISIBLE);
                            yukaripopup.dismiss();
                            kontrol=3;
                        }
                        else if (popupgiris.getText().toString().equals("6") && kontrol==4)
                        {
                            EtYukari3.setVisibility(View.VISIBLE);
                            yukaripopup.dismiss();
                            kontrol=5;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                yukaripopup.show();
            }
        });

        asagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asagipopup.setContentView(R.layout.asagipopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=asagipopup.findViewById(R.id.editText);
                btntamam=asagipopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        asagipopup.dismiss();
                    }
                });
                asagipopup.show();
            }
        });

        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solpopup.setContentView(R.layout.solpopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=solpopup.findViewById(R.id.editText);
                btntamam=solpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String log=popupgiris.getText().toString();
                        Log.w(log,"asdsd");
                        if(popupgiris.getText().toString().equals("2") && kontrol==3)
                        {
                            EtSol.setVisibility(View.VISIBLE);
                            solpopup.dismiss();
                            kontrol=4;
                        }
                        else {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                solpopup.show();
            }
        });

        sag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sagpopup.setContentView(R.layout.sagpopup);

                Button btntamam;
                final EditText popupgiris;
                popupgiris=sagpopup.findViewById(R.id.editText);
                btntamam=sagpopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(popupgiris.getText().toString().equals("4") && kontrol==1)
                        {
                            EtSag.setVisibility(View.VISIBLE);
                            sagpopup.dismiss();
                            kontrol=2;
                        }
                        else if (popupgiris.getText().toString().equals("5") && kontrol==5)
                        {
                            EtSag2.setVisibility(View.VISIBLE);
                            uc.setVisibility(View.VISIBLE);
                            sagpopup.dismiss();
                            kontrol=6;
                        }
                        else
                        {
                            Toast.makeText(GridActivity.this, "Yanlış Değer Girdiniz.Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                sagpopup.show();
            }
        });
/*
        donme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donmepopup.setContentView(R.layout.donmepopup);

                Button btntamam;
                btntamam=donmepopup.findViewById(R.id.btnbaglan);
                btntamam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        donmepopup.dismiss();
                    }
                });
                donmepopup.show();
            }
        });

*/
    }
}