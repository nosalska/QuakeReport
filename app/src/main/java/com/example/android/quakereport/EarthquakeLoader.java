package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.List;

import static android.view.View.GONE;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader (Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading kicked off");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground kicked off");
        if (mUrl == null) {
            return null;
        }
        List<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakeList;
    }
}
