package com.example.ouz.parrot.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ouz.parrot.ARSDK;
import com.example.ouz.parrot.R;

public class MainActivity extends AppCompatActivity {

    Dialog popupdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popupdialog=new Dialog(this);
    }

    public void GosterPopup(View v)
    {
        Intent intent = new Intent(this,DeviceListActivity.class);
        startActivity(intent);
        /*
        TextView textClose;
        popupdialog.setContentView(R.layout.popup);

        textClose=popupdialog.findViewById(R.id.txtclose);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupdialog.dismiss();
            }
        });
        popupdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupdialog.show();*/
    }

}
