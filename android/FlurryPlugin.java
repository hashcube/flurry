package com.tealeaf.plugin.plugins;

/* tealeaf imports */
import com.tealeaf.plugin.IPlugin;
import com.tealeaf.logger;

/* android imports */
import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.content.pm.PackageManager;

import org.json.JSONException;
import org.json.JSONObject;
/* flurry imports */
import com.flurry.android.FlurryAgent;

public class FlurryPlugin implements IPlugin {
    Activity _activity;
    final String TAG = "{{FLURRY}}";

    public void onCreate(Activity activity, Bundle savedInstanceState) {
      _activity = activity;
    }

    public void onStart() {
      private String FLURRY_API_KEY = null;
      PackageManager manager = _activity.getPackageManager();

      Bundle meta = manager.getApplicationInfo(_activity.getPackageName(), PackageManager.GET_META_DATA).metaData;

      if (meta != null) {
        FLURRY_API_KEY = meta.get('flurryAPIKey').toString();
      }

      if (FLURRY_API_KEY != null) {
        logger.log(TAG, 'Initialized with API key ' + FLURRY_API_KEY);
        FlurryAgent.onStartSession(_activity, FLURRY_API_KEY);
      }
    }

    public void onStop() {
      FlurryAgent.onEndSession(_activity);
    }

    public void track(String eventData) {
      HashMap <String, String> eventMap = new HashMap<String, String>();
      String name = null;
      Boolean isTimed = false;
      try {
        JSONObject eventDataJSON = new JSONObject(eventParams);

        name = JSONObject.get('name');
        isTimed = JSONObject.get('timed')

        JSONObject eventParamsJSON = eventDataJSON.get("data");
        Iterator<?> keys = eventParamsJSON.keys();

        while (keys.hasNext()) {
          String key = keys.next();
          String value = eventParamsJSON.get(key);
          eventMap.put(key, value);
        }
      } catch (JSONException e) {
        e.printStacktrace();
      }
      FlurryAgent.logEvent(name, eventMap, isTimed);
    }

    public void endTimedEvent(String name) {
      FlurryAgent.endTimedEvent(name);
    }
    public void onCreateApplication(Context applicationContext) {}
    public void onResume() {}
    public void onPause() {}
    public void onDestroy() {}
    public void onNewIntent(Intent intent) {}
    public void onActivityResult(Integer request, Integer result, Intent data){}
    public void setInstallReferrer(String referrer){}
}
