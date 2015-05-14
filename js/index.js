function pluginSend(event, params, stringify) {
  var params = stringify ? JSON.stringify(params) : params;

  NATIVE.plugins.sendEvent('FlurryPlugin', event, params);
};

exports = new (Class(function () {
  var TAG = '{{FLURRY}}';
  this.track = function (event_data) {
    event_data.data = event_data.data || {};
    event_data.timed = event_data.timed || false;
    if (!event_data.name) {
      logger.log(TAG, 'track','Error: No event name specified');
      return;
    }
    logger.log(TAG, 'track','Tracking event ' +
      event_data.name, JSON.stringify(event_data.data),
      "\n\n", 'started at ' + new Date());
    pluginSend('track', event_data, true);
  };

  this.endTimedEvent = function (name) {
    if (!name) {
      logger.log(TAG, 'endTimedEvent', 'Error: No event name specified');
      return;
    }
    logger.log(TAG, 'endTimedEvent', name, new Date());
    pluginSend('endTimedEvent', name);
  }

  return this;
}))();
