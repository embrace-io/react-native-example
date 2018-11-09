// @flow

import { NativeModules } from 'react-native';

export const endAppStartup = () =>
  NativeModules.EmbraceManager.endAppStartup();

export const setUserIdentifier = (userIdentifier: String) =>
  NativeModules.EmbraceManager.setUserIdentifier(userIdentifier);

export const clearUserIdentifier = () =>
  NativeModules.EmbraceManager.clearUserIdentifier();

export const setUsername = (username: String) =>
  NativeModules.EmbraceManager.setUsername(username);

export const clearUsername = () =>
  NativeModules.EmbraceManager.clearUsername();

export const setUserEmail = (userEmail: String) =>
  NativeModules.EmbraceManager.setUserEmail(userEmail);

export const clearUserEmail = () =>
  NativeModules.EmbraceManager.clearUserEmail();

export const logBreadcrumb = (message: String) =>
  NativeModules.EmbraceManager.logBreadcrumb(message);

export const logScreen = (screenName: String) =>
  NativeModules.EmbraceManager.logBreadcrumb(`Opening screen [${screenName}]`);

export const startMoment = (name: String, identifier: ?String, properties: ?Dictionary<Any>) => {
  if (identifier && properties) {
    NativeModules.EmbraceManager.startMomentWithNameAndIdentifierAndProperties(name, identifier, properties);
  } else if (identifier) {
    NativeModules.EmbraceManager.startMomentWithNameAndIdentifier(name, identifier);
  } else {
    NativeModules.EmbraceManager.startMomentWithName(name);
  }
}

export const startMomentAllowingScreenshot = (name: String, allowScreenshot: Boolean, identifier: ?String, properties: ?Dictionary<Any>) => {
  if (identifier && properties) {
    NativeModules.EmbraceManager.startMomentWithNameAndIdentifierAndPropertiesAllowingScreenshot(name, identifier, properties, allowScreenshot);
  } else if (identifier) {
    NativeModules.EmbraceManager.startMomentWithNameAndIdentifierAllowingScreenshot(name, identifier, allowScreenshot);
  } else {
    NativeModules.EmbraceManager.startMomentWithNameAllowingScreenshot(name, allowScreenshot);
  }
}

export const endMoment = (name: String, identifier: ?String) => {
  if (identifier) {
    NativeModules.EmbraceManager.endMomentWithNameAndIdentifier(name, identifier);
  } else {
    NativeModules.EmbraceManager.endMomentWithName(name);
  }
}

export const setUserPersona = (persona: String) =>
  NativeModules.EmbraceManager.setUserPersona(persona);

export const clearUserPersona = (persona: String) =>
  NativeModules.EmbraceManager.clearUserPersona(persona);

export const WARNING = 'warning';
export const INFO = 'info';
export const ERROR = 'error';

export const logMessage = (message: String, severity: 'info' | 'warning' | 'error' = 'error', properties: ?Dictionary<Any>) => {
  if (properties) {
    NativeModules.EmbraceManager.logMessageWithSeverityAndProperties(message, severity, properties);
  } else {
    NativeModules.EmbraceManager.logMessageWithSeverity(message, severity);
  }
}
