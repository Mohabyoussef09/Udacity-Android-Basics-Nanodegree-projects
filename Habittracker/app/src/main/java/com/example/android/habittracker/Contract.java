package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by mohab on 8/27/2017.
 */

public class Contract {

    public final class Medicine implements BaseColumns {
        public static final String TABLE_NAME = "medicine";
        public static final String medicine_name = "medicine_name";
        public static final String no_of_tablet_per_day = "tablet_no";

    }

}
