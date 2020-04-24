// Change to the package name of your application
package com.embrace;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Nonnull;

import io.embrace.android.embracesdk.Embrace;

public class EmbraceManagerModule extends ReactContextBaseJavaModule {

    public EmbraceManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "EmbraceManager";
    }

    @ReactMethod
    public void endAppStartup() {
        Embrace.getInstance().endAppStartup();
    }

    @ReactMethod
    public void setUserIdentifier(String userIdentifier) {
        Embrace.getInstance().setUserIdentifier(userIdentifier);
    }

    @ReactMethod
    public void setUsername(String username) {
        Embrace.getInstance().setUsername(username);
    }

    @ReactMethod
    public void setUserEmail(String userEmail) {
        Embrace.getInstance().setUserEmail(userEmail);
    }

    @ReactMethod
    public void clearUserEmail() {
        Embrace.getInstance().clearUserEmail();
    }

    @ReactMethod
    public void clearUserIdentifier() {
        Embrace.getInstance().clearUserIdentifier();
    }

    @ReactMethod
    public void clearUsername() {
        Embrace.getInstance().clearUsername();
    }

    @ReactMethod
    public void logBreadcrumb(String message) {
        Embrace.getInstance().logBreadcrumb(message);
    }

    @ReactMethod
    public void startMomentWithName(String name) {
        Embrace.getInstance().startEvent(name);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifier(String name, String identifier) {
        Embrace.getInstance().startEvent(name, identifier);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifierAndProperties(String name, String identifier, ReadableMap properties) {
        try {
            final Map<String, Object> props = properties != null ? properties.toHashMap() : null;
            Embrace.getInstance().startEvent(name, identifier, false, props);
        } catch (Exception e) {
            Log.e("Embrace", "Error starting moment with name, identifier, and properties", e);
        }
    }

    @ReactMethod
    public void startMomentWithNameAllowingScreenshot(String name, Boolean allowScreenshot) {
        Embrace.getInstance().startEvent(name, null, allowScreenshot);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifierAllowingScreenshot(String name, String identifier,
            Boolean allowScreenshot) {
        Embrace.getInstance().startEvent(name, identifier, allowScreenshot);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifierAndPropertiesAllowingScreenshot(String name, String identifier,
            ReadableMap properties, Boolean allowScreenshot) {
        try {
            final Map<String, Object> props = properties != null ? properties.toHashMap() : null;
            Embrace.getInstance().startEvent(name, identifier, allowScreenshot, props);
        } catch (Exception e) {
            Log.e("Embrace", "Error starting moment with name, identifier, properties, and allowScreenshot", e);
        }
    }

    @ReactMethod
    public void endMomentWithName(String name) {
        Embrace.getInstance().endEvent(name);
    }

    @ReactMethod
    public void endMomentWithNameAndIdentifier(String name, String identifier) {
        Embrace.getInstance().endEvent(name, identifier);
    }

    @ReactMethod
    public void setUserPersona(String persona) {
        Embrace.getInstance().setUserPersona(persona);
    }

    @ReactMethod
    public void clearUserPersona(String persona) {
        Embrace.getInstance().clearUserPersona(persona);
    }

    @ReactMethod
    public void clearAllUserPersonas() {
        Embrace.getInstance().clearAllUserPersonas();
    }

    @ReactMethod
    public void logMessageWithSeverity(String message, String severity) {
        if (severity.equals("info")) {
            Embrace.getInstance().logInfo(message);
        } else if (severity.equals("warning")) {
            Embrace.getInstance().logWarning(message);
        } else {
            Embrace.getInstance().logError(message);
        }
    }

    @ReactMethod
    public void logMessageWithSeverityAndProperties(String message, String severity, ReadableMap properties,
            Boolean allowScreenshot, String stacktrace) {
        try {
            final Map<String, Object> props = properties != null ? properties.toHashMap() : null;
            if (severity.equals("info")) {
                Embrace.getInstance().logInfo(message, props);
            } else if (severity.equals("warning")) {
                Embrace.getInstance().logWarning(message, props, allowScreenshot, stacktrace);
            } else {
                Embrace.getInstance().logError(message, props, allowScreenshot, stacktrace);
            }
        } catch (Exception e) {
            Log.e("Embrace", "Error logging message", e);
        }
    }

    @ReactMethod
    public void startView(String screen) {
        Embrace.getInstance().startFragment(screen);
    }

    @ReactMethod
    public void endView(String screen) {
        Embrace.getInstance().endFragment(screen);
    }

    @ReactMethod
    public void logHandledError(String message, String javascriptStackTrace) {
        Embrace.getInstance().logError(message, null, false, javascriptStackTrace, true);
    }

    @ReactMethod
    public void logUnhandledJSException(String name, String message, String type, String stacktrace) {
        Embrace.getInstance().logUnhandledJsException(name, message, type, stacktrace);
    }

    @ReactMethod
    public void setJavaScriptPatchNumber(String number) {
        Embrace.getInstance().setJavaScriptPatchNumber(number);
    }

    @ReactMethod
    public void setReactNativeVersion(String version) {
        Embrace.getInstance().setReactNativeVersionNumber(version);
    }

    @ReactMethod
    public void checkAndSetCodePushBundleURL() {
        try {
            Class<?> clazz = Class.forName("com.microsoft.codepush.react.CodePush");
            Method method = clazz.getDeclaredMethod("getJSBundleFile", null);
            String bundlePath = (String) method.invoke(null, null);
            Embrace.getInstance().setJavaScriptBundleURL(bundlePath);
        } catch (Exception e) {
            Log.i("Embrace", "CodePush not present in build.", e);
        }
    }

    @ReactMethod
    public void addSessionProperty(String key, String value, boolean permanent, Promise promise) {
        Boolean success = Embrace.getInstance().addSessionProperty(key, value, permanent);
        promise.resolve(success);
    }

    @ReactMethod
    public void removeSessionProperty(String key) {
        Embrace.getInstance().removeSessionProperty(key);
    }

    @ReactMethod
    public void getSessionProperties(Promise promise) {
        Map<String, String> properties = Embrace.getInstance().getSessionProperties();

        WritableMap propsMap = new WritableNativeMap();

        for (Map.Entry<String, String> prop : properties.entrySet()) {
            propsMap.putString(prop.getKey(), prop.getValue());
        }
        promise.resolve(propsMap);

    }
}
