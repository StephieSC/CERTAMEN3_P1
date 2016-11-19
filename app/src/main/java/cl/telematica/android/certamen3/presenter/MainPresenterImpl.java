package cl.telematica.android.certamen3.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.database.DataBaseClass;
import cl.telematica.android.certamen3.model.Feed;
import cl.telematica.android.certamen3.MainActivity;

/**
 * Created by Stephie on 18-11-2016.
 */

public class MainPresenterImpl implements MainPresenter {


    public List<Feed> getFeeds(String result) {
        List<Feed> feeds = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject responseData = jsonObject.getJSONObject("responseData");
            JSONObject feedObj = responseData.getJSONObject("feed");

            JSONArray entries = feedObj.getJSONArray("entries");
            int size = entries.length();
            for(int i = 0; i < size; i++){
                JSONObject entryObj = entries.getJSONObject(i);
                Feed feed = new Feed();

                feed.setTitle(entryObj.optString("title"));
                feed.setLink(entryObj.optString("link"));
                feed.setAuthor(entryObj.optString("author"));
                feed.setPublishedDate(entryObj.optString("publishedDate"));
                feed.setContent(entryObj.optString("content"));
                feed.setImage(entryObj.optString("image"));

                feeds.add(feed);
            }

            return feeds;
        } catch (JSONException e) {
            e.printStackTrace();
            return feeds;
        }
    }
    public static void saveToDataBase(DataBaseClass dbInstance, Feed model){
        SQLiteDatabase db = dbInstance.getWritableDatabase();
        if(db != null){
            db.beginTransaction();
            try{
                    db.execSQL("INSERT INTO feeds (title, link, author, publisheddate, content, image) " +
                            "VALUES ('" + model.title + "', '" +
                            model.link + "', '" +
                            model.author + "', '" +
                            model.publishedDate + "', '" +
                            model.content + "', '" +
                            model.image + "')");

            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }

    public static void deleteToDataBase(DataBaseClass dbInstance, Feed model){
        SQLiteDatabase db = dbInstance.getWritableDatabase();
        String[] args = new String[]{model.getTitle()};
        if(db != null){
            db.beginTransaction();
            try{
                db.execSQL("DELETE FROM feeds WHERE title=?", args);

            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }
    public static ArrayList<Feed> chargeFromDataBase(DataBaseClass dbInstance, ArrayList<Feed> lista){
        SQLiteDatabase db = dbInstance.getWritableDatabase();
        try{
            Cursor c = db.rawQuery("SELECT * FROM feeds", null);
             int i=0;
            while (c.moveToNext()) {
                lista[i].setTitle() = c.getString(c.getColumnIndex("title"));
                lista[i].setLink() = c.getString(c.getColumnIndex("link"));
                lista[i].setAuthor() = c.getString(c.getColumnIndex("author"));
                lista[i].setPublishedDate() = c.getString(c.getColumnIndex("publisheddate"));
                lista[i].setContent() = c.getString(c.getColumnIndex("content"));
                lista[i].setImage() = c.getString(c.getColumnIndex("image"));
                i++;

            }}finally{
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        return lista;
        }






}
