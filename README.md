# Welcome to the React Native Guide!

Embrace gathers the information needed to identify issues and measure performance automatically upon integration. The following React Native guide simply instructs on how to call the relevant functions so teams can provide much needed additional context to themselves (logs and user info) and measure the timing of key areas of their app explicitly (moments.)

For additional functionality and explanations please refer to the [React Native Guide](https://docs.embrace.io/docs/quick-quide).

# Approach
We purposefully built a solution for React that is as extremely light weight and bucks a few trends. There is no reason to utilize a bulkier and more app invasive approach via NPM and native React calls, when all that is needed is a simple set of JS files that wrap existing calls from the iOS and Android SDKs.

# iOS Integration
### Step 1: Initialize the Embrace Native iOS SDK
Follow the steps in the iOS Quick Integration in order to add and initialize the Embrace SDK.

### Step 2: Add the Embrace Manager
Copy EmbraceManager.h and EmbraceManager.m into the iOS React Native folder. (This guide provides a great example and walkthrough for the placement of the files when using a CocoaPod configuration.) The Embrace Manager files can be found on Github with the example on how to use React Native with Embrace.

_Continue to the React Integration Steps below._

# Android Integration
### Step 1: Initialize the Embrace Native Android SDK
Follow the steps in the Android Quick Integration in order to add and initialize the Embrace SDK.

### Step 2: Add the Embrace Manager
Copy EmbraceManagerPackage.java and EmbraceManagerModule.java into the Android React Native folder. These file have to be in the same hierarchy level as MainApplication.java. The Embrace Manager files can be found on Github with the example on how to use React Native with Embrace.

Step 3: Add EmbraceManager to the package list
In MainApplication.java, under the getPackages() function, add a new instance of the EmbraceManagerPackage to the returning array.

```
@Override
protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new EmbraceManagerPackage() <--- New line
    );
}
```

_Continue to the React Integration Steps in the next section._

# React Integration Steps
After completing the Android and/or iOS steps below, please continue with the following steps.

### Step 1: Import and Call Methods
Copy embrace.js into your JS codebase. Once complete, methods can be imported and called from this file.

A good starting place is ending your startup moment. This moment starts automatically with the code added to AppDelegate.m on iOS or MainActivity.java on Android, and you should end on Component mount similar to the below code.

```
import {endAppStartup} from './embrace';

type Props = {};
export default class App extends Component<Props> {
  componentDidMount() {
    endAppStartup();
  }
}
```

### Step 2: Integrate the User Identifier, Logs and Moments into your App
Since React Native is built with either an iOS or Android native framework, many of the functionality integrated to effectively use Embrace can be called either in Swift / Obj-C, Java, or Javascript. Please follow the remainder of the iOS Quick Integration or Android Quick Integration for the steps to complete your integration.

_The following calls located in the file, embrace.js, are listed below for reference._

```
// Startup: Call each place a startup may conclude.  Remember those deeplinks!
endAppStartup()

// User Identifiers: Set (or clear) one or more User Identifiers on app start up or registration
setUserIdentifier(userIdentifier: String)
clearUserIdentifier()

setUsername (username: String) 
clearUsername()

setUserEmail(userEmail: String) 
clearUserEmail()

// Breadcrumbs: Log a Breadcrumb to display these on the User Timelines
logBreadcrumb(message: String)

// Views: Log a Screen as screens are not automatically detected when in JS
logScreen(screenName: String)

// Moments : Start and End a Moment.  
// For ex, a purchase is from click-to-purchase to both success or failure
// For more information on Moments, please refer to the docs in the links below

startMoment(name: String, identifier: ?String, properties: ?Dictionary<Any>) 

startMomentAllowingScreenshot (name: String, allowScreenshot: Boolean, identifier: ?String, properties: ?Dictionary<Any>)

endMoment(name: String, identifier: ?String)

//Logs : Logs are aggregated and searchable for insights and finding specific carts, users and other properties.
//Logs are sent immediately to assure effective replays of problematic sessions
export const WARNING = 'warning';
export const INFO = 'info';
export const ERROR = 'error';

logMessage = (message: String, severity: 'info' | 'warning' | 'error' = 'error', properties: ?Dictionary<Any>)

// Personas : Include a user in a segment
// User personas are of specific types. For more info, please refer to the docs in the links below
setUserPersona(persona: String)

clearUserPersona = (persona: String)

// Startup: Call each place a startup may conclude.  Remember those deeplinks!
endAppStartup()

// User Identifiers: Set (or clear) one or more User Identifiers on app start up or registration
setUserIdentifier(userIdentifier: String)
clearUserIdentifier()

setUsername (username: String) 
clearUsername()

setUserEmail(userEmail: String) 
clearUserEmail()

// Breadcrumbs: Log a Breadcrumb to display these on the User Timelines
logBreadcrumb(message: String)

// Views: Log a Screen as screens are not automatically detected when in JS
logScreen(screenName: String)

// Moments : Start and End a Moment.  
// For ex, a purchase is from click-to-purchase to both success or failure
// For more information on Moments, please refer to the docs in the links below

startMoment(name: String, identifier: ?String, properties: ?Dictionary<Any>) 

startMomentAllowingScreenshot (name: String, allowScreenshot: Boolean, identifier: ?String, properties: ?Dictionary<Any>)

endMoment(name: String, identifier: ?String)

//Logs : Logs are aggregated and searchable for insights and finding specific carts, users and other properties.
//Logs are sent immediately to assure effective replays of problematic sessions
export const WARNING = 'warning';
export const INFO = 'info';
export const ERROR = 'error';

logMessage = (message: String, severity: 'info' | 'warning' | 'error' = 'error', properties: ?Dictionary<Any>)

// Personas : Include a user in a segment
// User personas are of specific types. For more info, please refer to the docs in the links below
setUserPersona(persona: String)
```
