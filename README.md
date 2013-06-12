# Game Closure DevKit Plugin: Flurry

## Usage

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"flurry"
],
~~~

Under the Android section, you can configure the Flurry plugin:

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"flurryKey": "MUmm2eD3qdBSPlcLb3qz",
		"flurryKeyStaging": "NUmm2eD3qdBSPlcLb3qx"
	}
~~~

To use Flurry logging in your game, install the plugin at the top of Application.js, like so:

~~~
import plugins.flurry.install;
~~~

Then send individual logs like this:

~~~
flurry.logEvent("myEvent", {
	"score": 999,
	"coins": 11,
	"isRandomParameter": true
});
~~~

## Testing

To test for successful integration, build your game:

~~~
basil debug native-android --clean --open
~~~

Then watch logcat:

~~~
adb logcat | grep flurry
~~~

If Flurry is hooked up properly, you'll see something like this:

~~~
D/FlurryAgent( 4673): Sending report to: http://data.flurry.com/aap.do
D/JS      ( 4673): LOG plugins.flurry.install {flurry} logEvent:  AppStart [object Object]
D/JS      ( 4673): LOG plugins.flurry.install {flurry} logEvent:  UpgradePriceGroup [object Object]
E/JS      ( 4673): {flurry} {android} logEvent - success: AppStart 
E/JS      ( 4673): {flurry} {android} logEvent - success: UpgradePriceGroup
~~~

(You'll see your own logs instead of AppStart and UpgradePriceGroup)

You can conclusively confirm events are going through on the Flurry website.

## Platform-specific notes

### Browsers

Nothing actually gets sent to Flurry in browsers, but you'll still see logs that look like this:

~~~
D/JS      ( 4673): LOG plugins.flurry.install {flurry} logEvent:  AppStart [object Object]
~~~

You can use these logs to implement tracking in your game.

### iOS

No iOS implementation at this time.

### Android

To use this plugin with Android, you'll need to set up a Flurry account and add your Flurry key to the manifest (see Usage section, above).