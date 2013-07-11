var Flurry = Class(function () {
	this.track = function (name, data) {
		logger.log("{flurry} logEvent: ", name, data);
		NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent &&
			NATIVE.plugins.sendEvent("FlurryPlugin", "logEvent",
				JSON.stringify({ eventName: name, params: data }));
	};
});

exports = new Flurry();