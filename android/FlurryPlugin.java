package com.tealeaf.plugin.plugins;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tealeaf.logger;
import com.tealeaf.TeaLeaf;
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

import com.flurry.android.FlurryAgent;


public class FlurryPlugin implements IPlugin {
    Activity activity;
    String flurryAppKey = "";
    boolean debug = false;

    public FlurryPlugin() {

    }

    private void initFlurry(Context context) {
        if (debug) {
		FlurryAgent.setLogEnabled(true);
		FlurryAgent.setLogEvents(true);
		FlurryAgent.setLogLevel(Log.VERBOSE);
        }

	FlurryAgent.init(context, flurryAppKey);
    }

    public void onCreateApplication(Context applicationContext) {
	try{
		PackageManager manager = applicationContext.getPackageManager();
		Bundle meta = manager.getApplicationInfo(applicationContext.getPackageName(), PackageManager.GET_META_DATA).metaData;
		flurryAppKey = meta.getString("FLURRY_KEY");
		debug = meta.getBoolean("WEEBY_DEBUG", true);
		initFlurry(applicationContext);
	} catch (Exception e) {
		logger.log("{flurry} setUser - failure: " + e.getMessage());
	}
   }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        this.activity = activity;
    }

    public void onResume() {

    }

    public void onStart() {
	FlurryAgent.onStartSession(activity);
    }

    public void onFirstRun() {
    }

    public void onRenderPause() {
    }

    public void onRenderResume() {
    } 

    public void setUser(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String user = obj.getString("user");

            FlurryAgent.setUserId(user);

            logger.log("{flurry} setUser - success: " + user);
        } catch (JSONException e) {
            logger.log("{flurry} setUser - failure: " + e.getMessage());
        }
    }

    public void track(String json) {
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
                    logger.log("{flurry} track - failure: " + eventName + " - " + e.getMessage());
                }

                if (value != null) {
                    params.put(key, value);
                }
            }
            FlurryAgent.logEvent(eventName, params);
            logger.log("{flurry} track - success: " + eventName);
        } catch (JSONException e) {
            logger.log("{flurry} track - failure: " + eventName + " - " + e.getMessage());
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
