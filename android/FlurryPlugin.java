package com.tealeaf.plugin.plugins;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tealeaf.logger;
import com.tealeaf.TeaLeaf;
import com.flurry.android.FlurryAgent;
import com.tealeaf.plugin.IPlugin;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tealeaf.util.HTTP;
import java.net.URI;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class FlurryPlugin implements IPlugin {
    Activity activity;

    public FlurryPlugin() {

    }

    public void onCreateApplication(Context applicationContext) {

    }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        this.activity = activity;

    }

    public void onResume() {

    }

    public void onStart() {
        PackageManager manager = activity.getPackageManager();
        String flurryKey = "";
        try {
            Bundle meta = manager.getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA).metaData;
            if (meta != null) {
                flurryKey = meta.getString("FLURRY_KEY");
            }
        } catch (Exception e) {
            android.util.Log.d("EXCEPTION", "" + e.getMessage());
        }

        FlurryAgent.setLogEnabled(true);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.setLogLevel(Log.VERBOSE);
        FlurryAgent.onStartSession(activity, flurryKey);
    }

    public void logEvent(String json) {
        String eventName = "noName";
        try {
            JSONObject obj = new JSONObject(json);
            eventName = obj.getString("eventName");
            Map<String, String> params = new HashMap<String, String>();
            JSONObject paramsObj = obj.getJSONObject("params");
            Iterator<String> iter = paramsObj.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                try {
                    value = paramsObj.getString(key);
                } catch (JSONException e) {
                    logger.log("{flurry} {android} logEvent - failure: " + eventName + " - " + e.getMessage());
                }

                if (value != null) {
                    params.put(key, value);
                }
            }
            FlurryAgent.logEvent(eventName, params);
            logger.log("{flurry} {android} logEvent - success: " + eventName);
        } catch (JSONException e) {
            logger.log("{flurry} {android} logEvent - failure: " + eventName + " - " + e.getMessage());
        }
    }

    public void onPause() {

    }

    public void onStop() {
        FlurryAgent.onEndSession(activity);
    }

    public void onDestroy() {
    }

    public void onNewIntent(Intent intent) {

    }

    public void setInstallReferrer(String referrer) {

    }

    public void onActivityResult(Integer request, Integer result, Intent data) {

    }

    public boolean consumeOnBackPressed() {
        return true;
    }

    public void onBackPressed() {
    }

}
