package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class InventoryAdapter extends ArrayAdapter<Inventory> {

    Button decrease_quantity;
    Inventory currentInventor;
    TextView quantity_txt;
    String prod_name_str;
    int quantity_int, price_int, quant;
    Context context;
    static int no_of_rows = 0;

    public InventoryAdapter(Activity context, ArrayList<Inventory> tours) {
        super(context, 0, tours);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        no_of_rows += 1;
        currentInventor = getItem(position);
        final TextView prod_name = (TextView) listItemView.findViewById(R.id.prod_name);
        prod_name_str = currentInventor.getProdName();
        prod_name.setText("Product Name: " + prod_name_str);

        quantity_txt = (TextView) listItemView.findViewById(R.id.quantity);
        quantity_int = currentInventor.getQuantity();
        quantity_txt.setText(("Quantity: " + String.valueOf(quantity_int)));

        TextView price = (TextView) listItemView.findViewById(R.id.price);
        price_int = currentInventor.getPrice();
        price.setText("Price: " + String.valueOf(price_int));

        ImageView img = (ImageView) listItemView.findViewById(R.id.image);
        String path = currentInventor.getImage();
        if (path == null)
            img.setImageResource(0);
        else
            img.setImageURI(Uri.parse(path));

        decrease_quantity = (Button) listItemView.findViewById(R.id.dec_btn);
        decrease_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                int position = listView.getPositionForView(parentRow);
                currentInventor = getItem(position);

                if (currentInventor.getQuantity() > 0) {
                    quant = currentInventor.getQuantity() - 1;
                    DbHelper dbHelper = new DbHelper(context);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    ContentValues cv = new ContentValues();
                    cv.put(Contract.inventory.PROD_NAME, currentInventor.getProdName()); //These Fields should be your String values of actual column names
                    cv.put(Contract.inventory.QUANTITY, String.valueOf(quant));
                    cv.put(Contract.inventory.PRICE, String.valueOf(currentInventor.getPrice()));
                    cv.put(Contract.inventory.IMAGE, currentInventor.getImage());

                    db.update(Contract.inventory.TABLE_NAME, cv, "prod_name=" + "'" + currentInventor.getProdName() + "'", null);
                    db.close();

                    Intent startIntent = new Intent(context, MainActivity.class);
                    context.startActivity(startIntent);
                } else
                    Toast.makeText(context, "Quantity can not be zero or negative value !!", Toast.LENGTH_LONG).show();
            }
        });
        return listItemView;
    }
}



