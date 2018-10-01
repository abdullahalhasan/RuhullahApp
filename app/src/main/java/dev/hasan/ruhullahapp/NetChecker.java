package dev.hasan.ruhullahapp;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by BDDL-102 on 10/1/2018.
 */

public class NetChecker {
    private Context context;

    public NetChecker(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            return true;
        }
    }
}
