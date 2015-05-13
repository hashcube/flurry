package com.tealeaf.plugin.plugins;


public class FlurryPlugin implements IPlugin {
    public void onCreate(Activity activity, Bundle savedInstanceState);
    public void onCreateApplication(Context applicationContext);
    public void onResume();
    public void onStart();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onNewIntent(Intent intent);
    public void onActivityResult(Integer request, Integer result, Intent data);
    public void setInstallReferrer(String referrer);
}
