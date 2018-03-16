package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.android.inventoryapp.Contract.inventory.TABLE_NAME;
import static com.example.android.inventoryapp.R.id.price_txt;
import static com.example.android.inventoryapp.R.id.quantity_txt;

public class SpecificItem extends AppCompatActivity {

    ListView listView;
    TextView specific_prod_name_txt, specific_price;
    ImageView specific_image;
    EditText specific_quantity_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_item);

        specific_prod_name_txt = (TextView) findViewById(R.id.product_name_txt);
        specific_image = (ImageView) findViewById(R.id.image);
        specific_price = (TextView) findViewById(price_txt);
        specific_quantity_txt = (EditText) findViewById(quantity_txt);
        specific_prod_name_txt.setText(MainActivity.specific_prod_item);
        specific_price.setText(String.valueOf(MainActivity.specific_price));
        specific_quantity_txt.setText(String.valueOf(MainActivity.specific_quantity));

        if ((MainActivity.specific_image) == null)
            specific_image.setImageResource(0);
        else
            specific_image.setImageURI(Uri.parse(MainActivity.specific_image));
    }

    public void save_btn(View v) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contract.inventory.PROD_NAME, specific_prod_name_txt.getText().toString()); //These Fields should be your String values of actual column names
        cv.put(Contract.inventory.QUANTITY, String.valueOf(specific_quantity_txt.getText().toString()));
        cv.put(Contract.inventory.PRICE, String.valueOf(specific_price.getText().toString()));

        db.update(TABLE_NAME, cv, "prod_name=" + "'" + specific_prod_name_txt.getText().toString() + "'", null);
        Toast.makeText(SpecificItem.this, "Item Saved", Toast.LENGTH_SHORT).show();
    }

    public void order_btn(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "orderss@shop.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "New Order");
        String body = "Product Name: " + specific_prod_name_txt.getText().toString() + "\n Quantity: " + specific_quantity_txt.getText().toString() + "\n Price: " + specific_price.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public void delete_btn(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SpecificItem.this);
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Are you sure you want to Delete item?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DbHelper dbHelper = new DbHelper(SpecificItem.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.execSQL("DELETE FROM " + Contract.inventory.TABLE_NAME + " WHERE " + Contract
                        .inventory.PROD_NAME + "= '" + specific_prod_name_txt.getText().toString() + "'");

                db.close();

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void clear_data() {
        specific_prod_name_txt.setText("");
        specific_quantity_txt.setText("");
        specific_price.setText("");
    }

    public void inc_quantity_btn(View v) {
        int specific_quantity;
        specific_quantity = Integer.parseInt(specific_quantity_txt.getText().toString());
        specific_quantity += 1;
        specific_quantity_txt.setText(String.valueOf(specific_quantity));
    }

    public void dec_quantity_btn(View v) {
        int specific_quantity;
        specific_quantity = Integer.parseInt(specific_quantity_txt.getText().toString());
        if (specific_quantity > 0)
            specific_quantity -= 1;
        else
            Toast.makeText(SpecificItem.this, "Quantity can not be zero or negative value !!", Toast.LENGTH_LONG).show();
        specific_quantity_txt.setText(String.valueOf(specific_quantity));
    }
}




