// Change to the package name of your application
package com.embrace;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;

import io.embrace.android.embracesdk.Embrace;

public class EmbraceManagerModule extends ReactContextBaseJavaModule {

    public EmbraceManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

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
    public void startMomentWithNameAndIdentifierAndProperties(String name, String identifier) {
        Embrace.getInstance().startEvent(name, identifier);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifierAllowingScreenshot(String name, String identifier, Boolean allowScreenshot) {
        Embrace.getInstance().startEvent(name, identifier, allowScreenshot);
    }

    @ReactMethod
    public void startMomentWithNameAndIdentifierAndPropertiesAllowingScreenshot(String name, String identifier, Object properties, Boolean allowScreenshot) {
        Embrace.getInstance().startEvent(name, identifier, allowScreenshot);
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
    public void logMessageWithSeverityAndProperties(String message, String severity, Map<String, Object> properties) {
        if (severity.equals("info")) {
            Embrace.getInstance().logInfo(message, properties);
        } else if (severity.equals("warning")) {
            Embrace.getInstance().logWarning(message, properties);
        } else {
            Embrace.getInstance().logError(message, properties);
        }
    }

}
