package cl.telematica.android.certamen3.presenter;

import java.util.List;

import cl.telematica.android.certamen3.database.DataBaseClass;
import cl.telematica.android.certamen3.model.Feed;

/**
 * Created by Stephie on 18-11-2016.
 */

public interface MainPresenter {

    List<Feed> getFeeds(String result);

    void saveToDataBase(DataBaseClass dbInstance, Feed list);

    void deleteToDataBase(DataBaseClass dbInstance, Feed model);

}
