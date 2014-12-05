# Game Closure DevKit Plugin: Flurry

## Installation
Install the module using the standard devkit install process:

~~~
devkit install https://github.com/gameclosure/flurry#v2.0.1
~~~

## Setup

Create a new app in the Flurry dashboard and add your application key to your
`manifest.json` file under the `ios` or `android` section as necessary.

Under the Android/iOS sections, you can configure the Flurry plugin:

~~~
    "android": {
        "flurryKey": "MUmm2eD3qdBSPlcLb3qz"
    }
~~~

~~~
    "ios": {
        "flurryKey": "MUmm2eD3qdBSPlcLb3qz"
    }
~~~

To use Flurry tracking in your game, import the flurry object:

~~~
import flurry;
~~~

Then send individual track events like this:

~~~
flurry.track("myEvent", {
    "score": 999,
    "coins": 11,
    "isRandomParameter": true
});
~~~


If you assign unique ids to your users you can send that on to flurry
so it can keep track of users in the same way you are:

~~~
flurry.setUserId(userId);
~~~


## Testing

To test for successful integration, build your game:

~~~
devkit debug native-android --clean --open
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
