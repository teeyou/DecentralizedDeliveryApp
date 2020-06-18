package model;

import android.content.Context;

public class Repository {
    private Repository instance;

    private Repository() {

    }

    public Repository getInstance(Context context) {
        if(instance == null)
            instance = new Repository();

        return instance;
    }
}
