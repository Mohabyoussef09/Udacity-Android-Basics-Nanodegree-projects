package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddInventory extends AppCompatActivity {

    private static final int SELECT_PICTURE = 100;
    ImageView img;
    EditText prod_name_txt, quantity_txt, price_txt;
    String prod_name, image;
    int quantity, price;
    String path;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        prod_name_txt = (EditText) findViewById(R.id.product_name_txt);
        quantity_txt = (EditText) findViewById(R.id.quantity_txt);
        price_txt = (EditText) findViewById(R.id.price_txt);
        img = (ImageView) findViewById(R.id.img);
    }

    @Override
    public void onBackPressed() {
        Intent startIntent = new Intent(this, MainActivity.class);
        this.startActivity(startIntent);
    }

    public void image_fn(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                path = selectedImageUri.toString();
                img.setImageURI(Uri.parse(path));
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void saveInformationToLocalDatabase(String prod_name, int quantity, int price, String image) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.saveToLocalDb(prod_name, quantity, price, image, db);
        dbHelper.close();
    }

    public void insert_fn(View v) {
        image = path;

        if (prod_name_txt.getText().toString().equals("") || quantity_txt.getText().toString().equals("") || price_txt.getText().toString().equals("") || image==null)
            Toast.makeText(this, "You must fill all fields !!", Toast.LENGTH_LONG).show();
        else {
            prod_name = prod_name_txt.getText().toString();
            quantity = Integer.parseInt(quantity_txt.getText().toString());
            price = Integer.parseInt(price_txt.getText().toString());
            path=null;
            saveInformationToLocalDatabase(prod_name, quantity, price, image);
            Intent startIntent = new Intent(this, MainActivity.class);
            this.startActivity(startIntent);

        }
    }

    public void clear_fn(View v) {
        clear_data();
    }

    public void clear_data() {
        prod_name_txt.setText("");
        quantity_txt.setText("");
        price_txt.setText("");
        path = "";
        img.setImageResource(0);
    }
}
