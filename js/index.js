function pluginSend(event, params) {
  NATIVE.plugins.sendEvent('FlurryPlugin', event, JSON.stringify(params || {}));
};

function pluginOn(event, callback) {
  NATIVE.events.registerHandler(event, callback);
};

exports = new (Class(function () {
  return this;
}))();
