package com.customsite.week2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DecimalFormat currency = new DecimalFormat("$###,##0.00");

        TextView items = (TextView) findViewById(R.id.txtItems);
        TextView price = (TextView) findViewById(R.id.txtPrice);
        TextView discount = (TextView) findViewById(R.id.txtDiscount);
        TextView subTotal = (TextView) findViewById(R.id.txtSubTotal);
        TextView salesTax = (TextView) findViewById(R.id.txtSalesTax);
        TextView total = (TextView) findViewById(R.id.txtTotal);

        items.setText(Integer.toString(MySessionVars.SessionItems));
        price.setText(currency.format(MySessionVars.SessionPrice));

        double discAmount = 0.0;
        if (MySessionVars.SessionProducts.size() >= ProductCatalog.ProductList.length) {
            discAmount = MySessionVars.SessionPrice * .20;
        }
        discount.setText(currency.format(discAmount));

        double subTotalAmount = MySessionVars.SessionPrice - discAmount;

        subTotal.setText(currency.format(subTotalAmount));

        double salesTaxAmount = MySessionVars.salesTaxRates.calcSalesTaxAmount(MySessionVars.SessionProducts);
        salesTax.setText(currency.format(salesTaxAmount));
        total.setText(currency.format(subTotalAmount + salesTaxAmount));

        Button home = (Button) findViewById(R.id.btnContinue);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
