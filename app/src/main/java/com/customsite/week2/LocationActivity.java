package com.customsite.week2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LocationActivity extends AppCompatActivity
        implements MapFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button start1 = (Button) findViewById(R.id.btnStart1);
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Get Current Location
                MySessionVars.salesTaxRates.setLocation(12345);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button start2 = (Button) findViewById(R.id.btnStart2);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView zip = (TextView) findViewById(R.id.txtZipCode);
                String strZip = zip.getText().toString();

                if (strZip.length() < 5) {
                    Toast.makeText(getApplicationContext(), "ZIP Code must be 5 digits", Toast.LENGTH_LONG).show();
                    return;
                }

                MySessionVars.salesTaxRates.setLocation(Integer.parseInt(strZip));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
