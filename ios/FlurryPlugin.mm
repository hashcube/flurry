#import "FlurryPlugin.h"
#import "Flurry.h"
#import "platform/log.h"

@implementation FlurryPlugin

// The plugin must call super dealloc.
- (void) dealloc {
	[super dealloc];
}

// The plugin must call super init.
- (id) init {
	self = [super init];
	if (!self) {
		return nil;
	}

	return self;
}

- (void) initializeWithManifest:(NSDictionary *)manifest appDelegate:(TeaLeafAppDelegate *)appDelegate {
	@try {
		NSDictionary *ios = [manifest valueForKey:@"ios"];
		NSString *flurryKey = [ios valueForKey:@"flurryKey"];

		//[Flurry setDebugLogEnabled:YES];
		[Flurry startSession:flurryKey];

		NSLOG(@"{flurry} Initialized with manifest flurryKey: '%@'", flurryKey);
	}
	@catch (NSException *exception) {
		NSLOG(@"{flurry} Failure to get ios:flurryKey from manifest file: %@", exception);
	}
}

- (void) setUser:(NSDictionary *)jsonObject {
	@try {
		NSString *userId = [jsonObject valueForKey:@"user"];

		[Flurry setUserID:userId];

		NSLOG(@"{flurry} Set user id: %@", userId);
	}
	@catch (NSException *exception) {
		NSLOG(@"{flurry} Exception while processing setUser:", exception);
	}
}

- (void) track:(NSDictionary *)jsonObject {
	@try {
		NSString *eventName = [jsonObject valueForKey:@"eventName"];
		
		NSDictionary *evtParams = [jsonObject objectForKey:@"params"];
		if (!evtParams || [evtParams count] <= 0) {
			[Flurry logEvent:eventName];

			NSLOG(@"{flurry} Delivered event '%@'", eventName);
		} else {
			[Flurry logEvent:eventName withParameters:evtParams];

			NSLOG(@"{flurry} Delivered event '%@' with %d params", eventName, (int)[evtParams count]);
		}
	}
	@catch (NSException *exception) {
		NSLOG(@"{flurry} Exception while processing event: ", exception);
	}
}

@end

