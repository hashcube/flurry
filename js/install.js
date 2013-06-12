GLOBAL.flurry = {
	logEvent: function(evtName, evtParams) {
		logger.log("{flurry} logEvent: ", evtName, evtParams);
		NATIVE && NATIVE.plugins && NATIVE.plugins.sendEvent &&
			NATIVE.plugins.sendEvent("FlurryPlugin", "logEvent",
				JSON.stringify({ eventName: evtName, params: evtParams }));
	}
};
