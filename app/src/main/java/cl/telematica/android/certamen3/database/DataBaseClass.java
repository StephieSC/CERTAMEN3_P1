package cl.telematica.android.certamen3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stephie on 18-11-2016.
 */

public class DataBaseClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CertamenDB";

    private String sqlString = "CREATE TABLE 'feeds' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'title' TEXT, " +
            "'link' TEXT, " +
            "'author' TEXT, " +
            "'publisheddate' TEXT, " +
            "'content' TEXT, " +
            "'image' TEXT, " ;

    public DataBaseClass(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'feeds'");
        onCreate(db);
    }

}
