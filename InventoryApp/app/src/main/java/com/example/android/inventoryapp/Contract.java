package com.example.android.inventoryapp;
import android.provider.BaseColumns;

public class Contract {

    public final class inventory implements BaseColumns {
        public static final String TABLE_NAME = "inventory_table";
        public static final String PROD_NAME = "prod_name";
        public static final String QUANTITY = "quantity";
        public static final String PRICE = "price";
        public static final String IMAGE = "image";
    }
}