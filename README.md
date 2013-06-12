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

You can test for successful integration on the Flurry website.
