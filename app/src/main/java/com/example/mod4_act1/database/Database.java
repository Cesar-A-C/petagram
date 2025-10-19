package com.example.mod4_act1.database;
import com.example.mod4_act1.R;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static Database instance;

    public Database(@Nullable Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized Database getInstance(Context context){
        if (instance==null){
            instance = new Database(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTblPets = "CREATE TABLE tbl_pets (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "unique_key TEXT NOT NULL UNIQUE," +
                "name TEXT NOT NULL, " +
                "image INTEGER,"+
                "likes INTEGER DEFAULT 0"+
                ")";

        String queryTblFavorites = "CREATE TABLE tbl_favorites(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "id_pet INTEGER NOT NULL, "+
                "FOREIGN KEY (id_pet) REFERENCES tbl_pets(id)"+
                ")";

        db.execSQL(queryTblPets);
        db.execSQL(queryTblFavorites);

        insertInitialPets(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_pets");
        db.execSQL("DROP TABLE IF EXISTS tbl_favorites");
        onCreate(db);
    }

    private void insertInitialPets(SQLiteDatabase db) {
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A001','Ramón', " + R.drawable.cat + ")");
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A002','Ignacio', " + R.drawable.cat + ")");
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A003','César', " + R.drawable.cat + ")");
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A004','Raúl', " + R.drawable.cat + ")");
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A005','Felipe', " + R.drawable.cat + ")");
        db.execSQL("INSERT INTO tbl_pets (unique_key, name, image) VALUES ('A006','Karencio', " + R.drawable.cat + ")");
    }

}
