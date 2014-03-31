var hasNativeEvents = NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent;

var Flurry = Class(function () {
	this.init = function () {
		this._globalProperties = {};
	}

	this.trackEvent =
	this.track = function (name, data) {
		// copy in global properties
		merge(data, this._globalProperties);

		if (DEBUG) {
			logger.log("track: ", name, JSON.stringify(data));
		}

		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("FlurryPlugin", "track", JSON.stringify({
					eventName: name,
					params: data
				}));
		}
	};

	this.setUserId = function (userId) {
		if (DEBUG) {
			logger.log("setUserId: ", userId);
		}

		if (hasNativeEvents) {
			NATIVE.plugins.sendEvent("FlurryPlugin", "setUser", JSON.stringify({
					user: userId
				}));
		}
	};

	this.setGlobalProperty = function (key, value) {
		this._globalProperties[key] = value;
	}
});

exports = new Flurry();
