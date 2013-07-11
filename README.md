# Game Closure DevKit Plugin: Flurry

## Usage

Install the plugin with `basil install flurry`.

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"flurry"
],
~~~

Under the Android/iOS sections, you can configure the Flurry plugin:

~~~
	"android": {
		"versionCode": 1,
		"icons": {
			"36": "resources/icons/android36.png",
			"48": "resources/icons/android48.png",
			"72": "resources/icons/android72.png",
			"96": "resources/icons/android96.png"
		},
		"flurryKey": "MUmm2eD3qdBSPlcLb3qz"
	}
~~~

~~~
	"ios": {
		"bundleID": "mmp",
		"appleID": "568975017",
		"version": "1.0.3",
		"icons": {
			"57": "resources/images/promo/icon57.png",
			"72": "resources/images/promo/icon72.png",
			"114": "resources/images/promo/icon114.png",
			"144": "resources/images/promo/icon144.png"
		},
		"flurryKey": "MUmm2eD3qdBSPlcLb3qz"
	},
~~~

To use Flurry tracking in your game, import the flurry object:

~~~
import plugins.flurry.flurry as flurry;
~~~

Then send individual track events like this:

~~~
flurry.track("myEvent", {
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
D/JS      ( 4673): LOG plugins.flurry.install {flurry} track:  AppStart [object Object]
D/JS      ( 4673): LOG plugins.flurry.install {flurry} track:  UpgradePriceGroup [object Object]
E/JS      ( 4673): {flurry} track - success: AppStart 
E/JS      ( 4673): {flurry} track - success: UpgradePriceGroup
~~~

(You'll see your own logs instead of AppStart and UpgradePriceGroup)

You can conclusively confirm events are going through on the Flurry website.

## Platform-specific notes

### Browsers

Nothing actually gets sent to Flurry in browsers, but you'll still see logs that look like this:

~~~
D/JS      ( 4673): LOG plugins.flurry.flurry {flurry} track:  AppStart [object Object]
~~~

You can use these logs to implement tracking in your game.
