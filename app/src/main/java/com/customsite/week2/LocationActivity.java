package com.customsite.week2;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


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

                TextView lat = (TextView) findViewById(R.id.txtLatitude);
                TextView lon = (TextView) findViewById(R.id.txtLongitude);
                double latitude = Double.parseDouble(lat.getText().toString());
                double longitude = Double.parseDouble(lon.getText().toString());

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(v.getContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Latitude must be > -90.0 & < 90.0 and longitude > -180.0 & < 180.0", Toast.LENGTH_LONG).show();
                    return;
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error Converting Latitude and Longitude", Toast.LENGTH_LONG).show();
                    return;
                }

                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String strZip = addresses.get(0).getPostalCode();

                if (strZip != null && strZip.length() == 5) {
                    Log.d("SalesTax", "Geocoder found address: " + address);
                    Log.d("SalesTax", "Geocoder found state: " + state);
                    Log.d("SalesTax", "Geocoder found country: " + country);
                    Log.d("SalesTax", "Geocoder found zip: " + strZip);
                    Toast.makeText(getApplicationContext(), "Using ZIP Code: " + strZip, Toast.LENGTH_LONG).show();

                    MySessionVars.salesTaxRates.setLocation(Integer.parseInt(strZip));

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Latitude and Longitude outside of US", Toast.LENGTH_LONG).show();
                }
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

    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }
}
