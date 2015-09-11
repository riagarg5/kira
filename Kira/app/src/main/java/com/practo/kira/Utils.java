package com.practo.kira;

import android.os.Build;

/**
 * Created by Ria on 9/11/15.
 */
public class Utils {

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}
