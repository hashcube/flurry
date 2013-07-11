var Flurry = Class(function () {
	this.track = function (name, data) {
		logger.log("{flurry} track: ", name, data);
		NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent &&
			NATIVE.plugins.sendEvent("FlurryPlugin", "track",
				JSON.stringify({ eventName: name, params: data }));
	};
});

exports = new Flurry();
