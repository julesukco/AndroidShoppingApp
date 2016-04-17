package com.customsite.week2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String productId = intent.getStringExtra("product");
        final String price;

        TextView productName = (TextView) findViewById(R.id.txtProduct);
        TextView description = (TextView) findViewById(R.id.txtDesc);
        TextView category = (TextView) findViewById(R.id.txtCategory);
        TextView taxRate = (TextView) findViewById(R.id.txtTaxRate);
        TextView amount = (TextView) findViewById(R.id.txtAmount);

        int pos = Integer.parseInt(productId);
        description.setText(ProductCatalog.ProductDescription[pos]);
        price = ProductCatalog.ProductPrice[pos];
        amount.setText("$" + price);
        productName.setText(ProductCatalog.ProductList[pos]);

        String type = ProductCatalog.ProductType[pos];
        category.setText("Category: " + type);
        double salesTaxRate;

        switch (type) {
            case "Food":
                salesTaxRate = MySessionVars.salesTaxRates.getSalesTaxFood();
                break;
            case "Clothing":
                salesTaxRate = MySessionVars.salesTaxRates.getSalesTaxClothing();
                break;
            default:
                salesTaxRate = MySessionVars.salesTaxRates.getSalesTaxOther();
                break;
        }
        DecimalFormat currency = new DecimalFormat("##0");
        taxRate.setText("Sales Tax: " + currency.format(salesTaxRate * 100) + "%");

        Button buy = (Button) findViewById(R.id.btnAdd);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = productId;
                double amount = Double.parseDouble(price);

                if (!MySessionVars.SessionProducts.containsKey(productId)) {
                    MySessionVars.SessionItems++;
                    MySessionVars.SessionPrice = MySessionVars.SessionPrice + amount;
                    MySessionVars.SessionProducts.put(productId, new Integer(1));
                } else {
                    Integer qty = (Integer) MySessionVars.SessionProducts.get(productId);
                    int intQty = qty.intValue();
                    intQty++;
                    MySessionVars.SessionItems++;
                    MySessionVars.SessionPrice = MySessionVars.SessionPrice + amount;
                    MySessionVars.SessionProducts.put(productId, new Integer(intQty));
                }

                Intent newActivity = new Intent(getApplicationContext(), SummaryActivity.class);
                startActivity(newActivity);
            }
        });

    }

}
