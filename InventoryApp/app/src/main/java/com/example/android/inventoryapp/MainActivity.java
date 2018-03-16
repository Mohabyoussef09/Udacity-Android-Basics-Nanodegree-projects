package com.example.android.inventoryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String specific_prod_item, specific_image;
    static int specific_quantity, specific_price;
    static DbHelper dbhelper;
    static ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new DbHelper(this);
        listView = (ListView) findViewById(R.id.list);
        context = this;
        load_data();
    }

    public void load_data() {
        final ArrayList<Inventory> tours = new ArrayList<Inventory>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = dbhelper.readFromLocalDb(db);
        int flag = 0;
        while (cursor.moveToNext()) {
            String prod_name = cursor.getString(cursor.getColumnIndex(Contract.inventory.PROD_NAME));
            int quality = cursor.getInt(cursor.getColumnIndex(Contract.inventory.QUANTITY));
            int price = cursor.getInt(cursor.getColumnIndex(Contract.inventory.PRICE));
            String image = cursor.getString(cursor.getColumnIndex(Contract.inventory.IMAGE));
            tours.add(new Inventory(prod_name, quality, price, image));
            flag = 1;
        }

        InventoryAdapter adapter = new InventoryAdapter(MainActivity.this, tours);
        if (flag == 1) {
            listView.setAdapter(adapter);
        } else {
            listView.setAdapter(null);
            View empty = getLayoutInflater().inflate(R.layout.empty_list_view, null, false);
            addContentView(empty, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            listView.setEmptyView(empty);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Inventory w = tours.get(position);
                specific_prod_item = w.getProdName();
                specific_quantity = w.getQuantity();
                specific_price = w.getPrice();
                specific_image = w.getImage();
                Intent intent = new Intent(MainActivity.this, SpecificItem.class);
                startActivity(intent);
            }
        });
    }

    public void add_fn(View v) {
        Intent i = new Intent(this, AddInventory.class);
        startActivity(i);
    }


    public Cursor readFromLocalDb(SQLiteDatabase db) {
        String[] projection1 = {Contract.inventory.PROD_NAME, Contract.inventory.QUANTITY, Contract.inventory.PRICE, Contract.inventory.IMAGE};
        Cursor cursor;
        cursor = db.query(Contract.inventory.TABLE_NAME, projection1, null, null, null, null, null);
        return cursor;
    }

    @Override
    protected void onStart() {
        super.onStart();
        load_data();
    }
}


