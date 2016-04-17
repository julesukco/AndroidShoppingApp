package com.customsite.week2;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;

public class SalesTaxRate {

    private static final double[] SALES_TAX_FOOD = {0.01, 0.02, 0.03, 0.04, 0.05};
    private static final double[] SALES_TAX_CLOTHING = {0.05, 0.06, 0.07, 0.08, 0.09};
    private static final double[] SALES_TAX_OTHER = {0.03, 0.04, 0.05, 0.06, 0.07};

    private double salesTaxFood;
    private double salesTaxClothing;
    private double salesTaxOther;

    public double calcSalesTaxAmount(HashMap shoppingCart) {
        double total = 0;
        String type;
        int productId;
        int qty;
        double price;

        Iterator it = shoppingCart.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            Log.d("SalesTax", pair.getKey() + " = " + pair.getValue());

            productId = Integer.parseInt(pair.getKey().toString());
            qty = Integer.parseInt(pair.getValue().toString());
            type = ProductCatalog.ProductType[productId];
            price = Double.parseDouble(ProductCatalog.ProductPrice[productId]);

            switch (type) {
                case "Food":
                    total = total + (salesTaxFood * price * qty);
                    break;
                case "Clothing":
                    total = total + (salesTaxClothing * price * qty);
                    break;
                default:
                    total = total + (salesTaxOther * price * qty);
                    break;
            }
        }

        return total;
    }

    public void setLocation(int zip) {

        if (zip < 20000) {
            salesTaxFood = SalesTaxRate.SALES_TAX_FOOD[0];
            salesTaxClothing = SalesTaxRate.SALES_TAX_CLOTHING[0];
            salesTaxOther = SalesTaxRate.SALES_TAX_OTHER[0];
        }
        else if (zip < 40000) {
            salesTaxFood = SalesTaxRate.SALES_TAX_FOOD[1];
            salesTaxClothing = SalesTaxRate.SALES_TAX_CLOTHING[1];
            salesTaxOther = SalesTaxRate.SALES_TAX_OTHER[1];
        }
        else if (zip < 60000) {
            salesTaxFood = SalesTaxRate.SALES_TAX_FOOD[2];
            salesTaxClothing = SalesTaxRate.SALES_TAX_CLOTHING[2];
            salesTaxOther =SalesTaxRate.SALES_TAX_OTHER[2];
        }
        else if (zip < 80000) {
            salesTaxFood = SalesTaxRate.SALES_TAX_FOOD[3];
            salesTaxClothing = SalesTaxRate.SALES_TAX_CLOTHING[3];
            salesTaxOther =SalesTaxRate.SALES_TAX_OTHER[3];
        }
        else {
            salesTaxFood = SalesTaxRate.SALES_TAX_FOOD[4];
            salesTaxClothing = SalesTaxRate.SALES_TAX_CLOTHING[4];
            salesTaxOther =SalesTaxRate.SALES_TAX_OTHER[4];
        }

        Log.d("SalesTax", "Sales Tax set: Food " + salesTaxFood +
                " Clothing " + salesTaxClothing +
                " Other " + salesTaxOther);
    }

}
